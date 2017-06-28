package com.bibinet.biunion.project.application;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by bibinet on 2017-5-18.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        JPushInterface.init(this);
        JPushInterface.setDebugMode(true);


        //环信配置
        //环信初始化配置
        //初始化环信
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        //初始化
        EMClient.getInstance().init(getApplicationContext(), options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }
}
