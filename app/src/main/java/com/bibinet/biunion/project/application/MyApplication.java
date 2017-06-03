package com.bibinet.biunion.project.application;

import android.app.Application;

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
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
