package com.bibinet.biunion.project.utils.cityselectutil;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


import com.bibinet.biunion.R;
import com.bibinet.biunion.project.bean.cityselectbean.BaseIndexPinyinBean;
import com.bibinet.biunion.project.builder.IIndexBarDataHelper;
import com.bibinet.biunion.project.builder.IndexBarDataHelperImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */
public class IndexBar extends View {
    private int mIndexBarPressedBackground;//手指按下的背景
    //#在最后面（默认的数据源）
    public static String[] INDEX_STRING = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};
    private List<String> mIndexList;//索引数据源

    private Paint mPaint;

    private boolean isNeedRealIndex;

    private int mWidth, mHeight;//view的宽高
    private int mGapHeight;//每个index区域的高度

    private IndexBar mIndexBar;

    private IIndexBarDataHelper mDataHelper;


    //下面是外部需要传的
    private TextView mHintTextView;//A,B,C,,,
    private LinearLayoutManager mLayoutManager;
    private List<? extends BaseIndexPinyinBean> mSourceDatas;//数据源
    private int mHeaderViewCount = 0;//头的个数
    private boolean isSourceDatasAlreadySorted;//源数据 已经有序？

    public IndexBar(Context context) {
        this(context, null);
    }

    public IndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
      initAttrs(context, attrs, defStyleAttr);
    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        int indexBarTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics());
        mIndexBarPressedBackground = Color.BLUE;//默认按下背景黑色
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.IndexBar, defStyleAttr, 0);
        int arrayCount = typeArray.getIndexCount();
        for (int i = 0; i < arrayCount; i++) {
            int attr = typeArray.getIndex(i);
            if (attr == R.styleable.IndexBar_indexBarTextSize) {
                indexBarTextSize = typeArray.getDimensionPixelSize(attr, indexBarTextSize);
            } else if (attr == R.styleable.IndexBar_indexBarPressBackground) {
                mIndexBarPressedBackground = typeArray.getColor(attr, mIndexBarPressedBackground);
            }
        }
        typeArray.recycle();
        initIndexDatas();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(indexBarTextSize);
        mPaint.setColor(Color.BLACK);
//        mIndexBar = (IndexBar) findViewById(R.id.indexbar);
        //设置index触摸监听器

        setOnIndexPressListener(new OnIndexPressListener() {
            @Override
            public void onIndexPressed(String tag) {
                if (mHintTextView != null) {
                    mHintTextView.setVisibility(View.VISIBLE);
                    mHintTextView.setText(tag);
                }

                if (mLayoutManager != null) {
                    int position = getPositionByTag(tag);
                    if (position != -1) {
                        mLayoutManager.scrollToPositionWithOffset(position, 0);
                    }

                }
            }

            @Override
            public void onTouchEnded() {
                if (mHintTextView != null) {
                    mHintTextView.setVisibility(View.GONE);
                }
            }
        });

        mDataHelper = new IndexBarDataHelperImpl();

    }

    /**
     * 初始化索引
     */
    private void initIndexDatas() {
        if (isNeedRealIndex) {
            mIndexList = new ArrayList<>();
        } else {
            mIndexList = Arrays.asList(INDEX_STRING);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        //默认宽高初始的值就是0
        int measureWidth = 0, measureHeight = 0;

        for (int i = 0; i < mIndexList.size(); i++) {

            String text = mIndexList.get(i);
            Rect textBound = new Rect();
            mPaint.getTextBounds(text, 0, text.length(), textBound);

            measureWidth = Math.max(textBound.width(), measureWidth);
            measureHeight = Math.max(textBound.height(), measureHeight);


        }
        measureHeight += measureHeight;

        switch (wMode) {
            case MeasureSpec.EXACTLY:
                measureWidth = wSize;
                break;
            case MeasureSpec.AT_MOST:
                measureWidth = Math.min(measureWidth, wSize);
                break;
            case MeasureSpec.UNSPECIFIED:
                break;

        }

        switch (hMode) {
            case MeasureSpec.EXACTLY:
                measureHeight = hSize;
                break;
            case MeasureSpec.AT_MOST:
                measureHeight = Math.min(measureHeight, hSize);
                break;
            case MeasureSpec.UNSPECIFIED:
                break;

        }
        setMeasuredDimension(measureWidth, measureHeight);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        int paddingTop = getPaddingTop();
        String text;
        for (int i = 0; i < mIndexList.size(); i++) {
            text = mIndexList.get(i);
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            int baseline = (int) ((mGapHeight - fontMetrics.bottom - fontMetrics.top) / 2);
            canvas.drawText(text, mWidth / 2 - mPaint.measureText(text) / 2, paddingTop + baseline + mGapHeight * i, mPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        //解决数据源为空的情况
        if (null == mIndexList || mIndexList.isEmpty()) {
            return;
        }
        computeGapHeight();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackgroundColor(mIndexBarPressedBackground);
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                //确定点击范围
                int pressY = (int) ((y - getPaddingTop()) / mGapHeight);
                if (pressY < 0) {
                    pressY = 0;
                }
                if (pressY >= mIndexList.size()) {
                    pressY = mIndexList.size() - 1;
                }
                if (onIndexPressListener != null && pressY > -1 && pressY < mIndexList.size()) {
                    onIndexPressListener.onIndexPressed(mIndexList.get(pressY));
                }


                break;
            case MotionEvent.ACTION_UP:
                setBackgroundResource(android.R.color.transparent);
                if (onIndexPressListener != null) {
                    onIndexPressListener.onTouchEnded();
                }
                break;
        }
        return true;
    }


    public boolean isSourceDatasAlreadySorted() {
        return isSourceDatasAlreadySorted;
    }

    /**
     * 源数据 是否已经有序
     *
     * @param sourceDatasAlreadySorted
     * @return
     */
    public IndexBar setSourceDatasAlreadySorted(boolean sourceDatasAlreadySorted) {
        isSourceDatasAlreadySorted = sourceDatasAlreadySorted;
        return this;
    }

    /**
     * 字母的TextView
     * 添加提示的
     *
     * @param hintTextView
     * @return
     */
    public IndexBar setHintTextView(TextView hintTextView) {
        this.mHintTextView = hintTextView;
        return this;
    }

    /**
     * 这是汉语转拼音，拼音成tag，数据源排序，获取indexTag四个方法的接口
     *
     * @return
     */
    public IIndexBarDataHelper getDataHelper() {
        return mDataHelper;
    }

    public IndexBar setDataHelper(IIndexBarDataHelper mDataHelper) {
        this.mDataHelper = mDataHelper;
        return this;
    }

    /**
     * 添加数据源
     *
     * @param sourceList
     * @return
     */

    public IndexBar setSourceList(List<? extends BaseIndexPinyinBean> sourceList) {
        this.mSourceDatas = sourceList;
        initSourceDatas();//对数据源进行初始化
        return this;
    }

    //初始化数据源
    private void initSourceDatas() {
        //解决数据源为空或者size为0的情况
        if (mSourceDatas.isEmpty() || null == mSourceDatas) {
            return;
        }
        if (!isSourceDatasAlreadySorted) {
            mDataHelper.sortSourceDatas(mSourceDatas);
        } else {
            mDataHelper.convert(mSourceDatas);
            mDataHelper.fillInexTag(mSourceDatas);
        }
        if (isNeedRealIndex) {
            mDataHelper.getSortIndexDatas(mSourceDatas, mIndexList);
            computeGapHeight();
        }

    }

    /**
     * 设置recycleView的manger
     *
     * @param manger
     * @return
     */
    public IndexBar setLayoutManger(LinearLayoutManager manger) {
        this.mLayoutManager = manger;
        return this;
    }


    /**
     * 一定要在设置数据源之前调用
     *
     * @param needRealIndex
     * @return
     */
    public IndexBar setNeedRealIndex(boolean needRealIndex) {
        isNeedRealIndex = needRealIndex;
        initIndexDatas();
        return this;
    }


    /**
     * 传入recycleView的头的个数
     *
     * @param mHeaderViewCount
     * @return
     */
    public IndexBar setHeaderViewCount(int mHeaderViewCount) {
        this.mHeaderViewCount = mHeaderViewCount;
        return this;

    }

    public int getHeaderViewCount() {
        return mHeaderViewCount;
    }

    /**
     * 计算单个index区域的高度
     */
    private void computeGapHeight() {
        mGapHeight = (mHeight - getPaddingTop() - getPaddingBottom()) / mIndexList.size();
    }


    public OnIndexPressListener onIndexPressListener;

    public OnIndexPressListener getOnIndexPressListener() {
        return onIndexPressListener;
    }

    public void setOnIndexPressListener(OnIndexPressListener onIndexPressListener) {
        this.onIndexPressListener = onIndexPressListener;
    }


    /**
     * 点击滑动右侧indexBar的点击事件
     */
    public interface OnIndexPressListener {
        void onIndexPressed(String tag);//当某个index被按下

        void onTouchEnded();//当触摸结束
    }

    private int getPositionByTag(String tag) {

        if (mSourceDatas == null || mSourceDatas.isEmpty()) {
            return -1;
        }
        if (TextUtils.isEmpty(tag)) {
            return -1;
        }
        for (int i = 0; i < mSourceDatas.size(); i++) {
            if (tag.equals(mSourceDatas.get(i).getIndexTag())) {
                return i + getHeaderViewCount();
            }

        }
        return -1;
    }

}
