package com.bibinet.biunion.project.utils;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.builder.MyViewPager;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by bibinet on 2017-5-17.
 */

public class BannerUtils {
    private MyViewPager viewPager;
    private boolean isRuning;
    private Context context;
    private List<String> urls;
    private int lastPosition;
    private LinearLayout groupContain;

    public BannerUtils(Context context, MyViewPager viewPager, LinearLayout groupContain, List<String> urls) {
        this.viewPager = viewPager;
        this.urls = urls;
        this.groupContain=groupContain;
        this.context=context;
    }
   public void startPlayBanner(){
       MyPageAdapter adapter=new MyPageAdapter();
       viewPager.setAdapter(adapter);
       isRuning=true;
       addPoint();
       handler.sendEmptyMessageDelayed(0,2000);
       viewPager.setOnViewPagerTouchListioner(new MyViewPager.OnTouchListioner() {
           @Override
           public void onActionDown() {
               handler.removeMessages(0);
           }

           @Override
           public void onActionMove() {
               handler.removeMessages(0);
           }

           @Override
           public void onActionUp() {
               handler.sendEmptyMessageDelayed(0,2000);
           }
       });
       viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
           }
           @Override
           public void onPageSelected(int position) {
               position = position % (urls.size());
               groupContain.getChildAt(position).setEnabled(true);
               groupContain.getChildAt(lastPosition).setEnabled(false);
               lastPosition = position;
           }
           @Override
           public void onPageScrollStateChanged(int state) {
           }
       });
   }
    private  Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg) {
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            if (isRuning) {
                handler.sendEmptyMessageDelayed(0,2000);
            }
        };
    };
    private void addPoint() {
        groupContain.removeAllViews();
        for(int i=0;i<urls.size();i++){
            ImageView iv=new ImageView(context);
            iv.setBackgroundResource(R.drawable.point_bg);
            if (i==0) {
                iv.setEnabled(true);
            } else {
                iv.setEnabled(false);
            }
            //因为要把view添加到一个线性布局，故需要一个线性布局的LayoutParams
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(DensityUtil.dip2px(context,8), DensityUtil.dip2px(context,8));
            params.rightMargin= DensityUtil.dip2px(context,12);
            iv.setLayoutParams(params);
            groupContain.addView(iv);
        }
    }
    class MyPageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return Integer.MAX_VALUE;
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0==arg1;
        }
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            // TODO Auto-generated method stub
            ImageView iv=new ImageView(context);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            // if (IsNetCanUse){
//            Glide.with(context).load(ProConstant.ImageUrls[position%(3)]).error(R.mipmap.ic_launcher).into(iv);
            Glide.with(context).load(urls.get(position%3)).error(R.mipmap.ic_launcher).into(iv);
            //}
            // iv.setImageBitmap(BitmapFactory.decodeResource(getResources(), pics[position%(pics.length)]));

            container.addView(iv);
            iv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                  /*  Intent intent=new Intent(getActivity(),BannerActivity.class);
                    intent.putExtra("position", String.valueOf(position%(pics.length)));
                    startActivity(intent);*/
                }
            });
            return iv;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView)object);
            object=null;//编码规范好
            //super.destroyItem(container, position, object);
        }
    }
}
