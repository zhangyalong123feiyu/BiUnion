package com.bibinet.biunion.project.builder;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by bibinet on 2017-3-7.
 */
public class MyViewPager extends ViewPager {
    private OnTouchListioner listioner;
    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
        		case MotionEvent.ACTION_DOWN:
                    if (listioner!=null){
                        listioner.onActionDown();
                    }
        			break;

        		case MotionEvent.ACTION_MOVE:
                    if (listioner!=null){
                        listioner.onActionMove();
                    }
        			break;

        		case MotionEvent.ACTION_UP:
                    if (listioner!=null){
                        listioner.onActionUp();
                    }
        			break;

        		default:
        			break;
        		}
        return super.dispatchTouchEvent(ev);
    }

    public void setOnViewPagerTouchListioner(OnTouchListioner listioner){
            this.listioner=listioner;
    }
    public interface OnTouchListioner{
        void onActionDown();
        void onActionMove();
        void onActionUp();
    }
}
