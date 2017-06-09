package com.bibinet.biunion.project.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;


import com.bibinet.biunion.project.builder.ISuspensionInterface;

import java.util.List;

/**
 * Created by zp on 2016/12/11.
 */

public class SuspensionDecoration extends RecyclerView.ItemDecoration {

    private List<? extends ISuspensionInterface> mDataList;
    private int mHeaderViewCount = 0;
    private int mTitleHeight;//悬浮头的高度
    private int getmTitleFont;//标记tag的颜色
    private int mTitleTextSize;//t标记ag的大小
    private Paint mPaint;
    private Rect mBound;//用于存放测量文字Rect

    private static int COLOR_TITLE_BG = Color.parseColor("#FFDFDFDF");
    private static int COLOR_TITLE_FONT = Color.parseColor("#FF999999");

    public int getmTitleHeight() {
        return mTitleHeight;
    }


    public SuspensionDecoration(Context context, List<? extends ISuspensionInterface> mDataList) {
        this.mDataList = mDataList;
        mPaint = new Paint();
        mBound = new Rect();
        mTitleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics());
        mTitleTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, context.getResources().getDisplayMetrics());
        mPaint.setTextSize(mTitleTextSize);
        mPaint.setAntiAlias(true);
    }


    public int getHeaderViewCount() {
        return mHeaderViewCount;
    }

    public SuspensionDecoration setHeaderViewCount(int mHeaderViewCount) {
        this.mHeaderViewCount = mHeaderViewCount;
        return this;
    }

    public SuspensionDecoration setColorTitleBg(int colorTitleBg) {
        COLOR_TITLE_BG = colorTitleBg;
        return this;
    }

    public SuspensionDecoration setColorTitleFont(int colorTitleFont) {
        COLOR_TITLE_FONT = colorTitleFont;
        return this;
    }

    public SuspensionDecoration setTitleFontSize(int mTitleFontSize) {
        mPaint.setTextSize(mTitleFontSize);
        return this;
    }

    public SuspensionDecoration setmTitleHeight(int mTitleHeight) {
        this.mTitleHeight = mTitleHeight;
        return this;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //初始时候super(0,0,0,0)
        super.getItemOffsets(outRect, view, parent, state);
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        position -= getHeaderViewCount();
        if (null == mDataList || mDataList.isEmpty() || position > mDataList.size() - 1) {
            return;
        }
        if (position > -1) {
            ISuspensionInterface iSuspensionInterface = mDataList.get(position);
            if (iSuspensionInterface.isShowSuspension()) {
                if (position == 0) {
                    outRect.set(0, mTitleHeight, 0, 0);
                } else if (null != iSuspensionInterface.getSuspensionTag() && !iSuspensionInterface.getSuspensionTag().equals(mDataList.get(position - 1).getSuspensionTag())) {
                    outRect.set(0, mTitleHeight, 0, 0);
                }
            }


        }


    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            int position = ((RecyclerView.LayoutParams) child.getLayoutParams()).getViewLayoutPosition();
            position -= getHeaderViewCount();
            if (null == mDataList || mDataList.isEmpty() || position > mDataList.size() - 1 ||position < 0|| !mDataList.get(position).isShowSuspension()) {
                continue;//越界
            }
            if (position > -1) {
                if (position == 0) {
                    drawTiele(c, parent, position, child);
                } else if (null != mDataList.get(position).getSuspensionTag() && !mDataList.get(position).getSuspensionTag().equals(mDataList.get(position - 1).getSuspensionTag())) {
                    drawTiele(c, parent, position, child);
                }

            }
        }


    }

    /**
     * 绘制悬浮头
     *
     * @param c
     * @param parent
     * @param position
     * @param child
     */
    private void drawTiele(Canvas c, RecyclerView parent, int position, View child) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        //绘制矩形框
        mPaint.setColor(COLOR_TITLE_BG);
        c.drawRect(left, child.getTop() - mTitleHeight, right, child.getTop(), mPaint);

        //绘制字
        mPaint.setColor(COLOR_TITLE_FONT);
        String tag = mDataList.get(position).getSuspensionTag();
        mPaint.getTextBounds(tag, 0, tag.length(), mBound);
        c.drawText(tag, child.getPaddingLeft(),
                child.getTop() -(mTitleHeight/2-mBound.height()/2), mPaint);
//        Log.e("drawTitle", "child.getPaddingLeft()"+child.getPaddingLeft()+"---child.gettop" + child.getTop() + "--titleHeight" + mTitleHeight + "--mbound.height" + mBound.height()+"--child.getBottom"+child.getBottom());


    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int position = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
        position -= getHeaderViewCount();
        if (null == mDataList || mDataList.isEmpty() || position > mDataList.size() - 1 || position < 0 || !mDataList.get(position).isShowSuspension()) {
            return;
        }
        String tag = mDataList.get(position).getSuspensionTag();
        View child = parent.findViewHolderForLayoutPosition(position + getHeaderViewCount()).itemView;
        boolean flag = false;
        if (mDataList.size() > position + 1) {
            if (null != tag && !tag.equals(mDataList.get(position + 1).getSuspensionTag())) {

                if (child.getTop() + child.getHeight() < mTitleHeight) {
                    c.save();
                    flag = true;
                    c.translate(0, child.getHeight() + child.getTop() - mTitleHeight);
                }
            }
        }

        mPaint.setColor(COLOR_TITLE_BG);
        c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getWidth() - parent.getPaddingRight(), parent.getPaddingTop() + mTitleHeight, mPaint);
        mPaint.setColor(COLOR_TITLE_FONT);
        mPaint.getTextBounds(tag, 0, tag.length(), mBound);
        c.drawText(tag, child.getPaddingLeft(),
                mTitleHeight - (mTitleHeight / 2 - mBound.height() / 2),
                mPaint);
//        Log.e("ondrawOver","child.getPaddingLeft()"+child.getPaddingLeft());


        if (flag)
            c.restore();




    }
}
