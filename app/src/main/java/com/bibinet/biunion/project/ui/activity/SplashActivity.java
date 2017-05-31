package com.bibinet.biunion.project.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.utils.SharedPresUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bibinet on 2017-5-31.
 */

public class SplashActivity extends BaseActivity {

    private SharedPresUtils shareutil;
    private boolean isFristUse;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(R.mipmap.ic_launcher);
        shareutil = SharedPresUtils.getsSharedPresUtils(SplashActivity.this);
        isFristUse=shareutil.getBoolean("isfirstuse",true);
        setContentView(imageView);
        initanimation(imageView);
    }
    private void initanimation(ImageView imageView) {
        Animation animation = new AlphaAnimation(0.7f, 1.0f);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                checkversion();

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                autoLogin();
                if (isFristUse) {
                        startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                        SplashActivity.this.finish();
                    }else{
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                        SplashActivity.this.finish();
                		}
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imageView.startAnimation(animation);
        animation.start();
    }

    private void autoLogin() {

    }

    private void checkversion() {

    }

}
