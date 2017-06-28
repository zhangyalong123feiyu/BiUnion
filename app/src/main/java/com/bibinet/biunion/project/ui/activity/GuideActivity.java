package com.bibinet.biunion.project.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.utils.DensityUtil;
import com.bibinet.biunion.project.utils.SharedPresUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-5-31.
 */

public class GuideActivity extends BaseActivity {
    @BindView(R.id.guide_viewpager)
    ViewPager guideViewpager;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.guide_container)
    LinearLayout guideContainer;
    private int[] photos={R.mipmap.yindaoye1,R.mipmap.yindaoye2,R.mipmap.yindaoye3,R.mipmap.yindaoye4};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);  // 隐藏状态栏
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        setpagerIndacter();
        setViewPager();
    }

    private void setViewPager() {
        guideViewpager.setAdapter(new GuideAdapter());
        guideViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int pos;
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                if(position==photos.length-1){
                    btnStart.setVisibility(View.VISIBLE);
                }else{
                    btnStart.setVisibility(View.GONE);
                }
                guideContainer.getChildAt(position).setEnabled(true);
                guideContainer.getChildAt(pos).setEnabled(false);
                pos=position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    @OnClick(R.id.btn_start)
    public void onViewClicked() {
        Intent intent=new Intent(GuideActivity.this,MainActivity.class);
        startActivity(intent);
        SharedPresUtils.getsSharedPresUtils(this).putBoolean("isfirstuse", false);
        finish();
    }
    private void setpagerIndacter() {
        // TODO Auto-generated method stub
        for(int i=0;i<photos.length;i++){
            View v=new View(this);
            v.setEnabled(false);//不可以用(表示一种状态)
            //设置view的布局(例如宽度高度)
            LinearLayout.LayoutParams params=
                    new LinearLayout.LayoutParams(DensityUtil.dip2px(GuideActivity.this,10),DensityUtil.dip2px(GuideActivity.this,10));
            params.leftMargin=DensityUtil.dip2px(GuideActivity.this,15);
            v.setLayoutParams(params);
            //设置view的背景
            v.setBackgroundResource(R.drawable.point_bg);
            //将view添加到布局中
            guideContainer.addView(v);
        }
        //将第0个设置enable状态为true
        guideContainer.getChildAt(0).setEnabled(true);

    }

    class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return photos.length;
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0==arg1;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            ImageView iv=new ImageView(getApplicationContext());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageResource(photos[position]);
            container.addView(iv);
            return iv;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
            container.removeView((View)object);
        }
    }

}
