package com.bibinet.biunion.project.application;

import android.app.Application;

import com.bibinet.biunion.project.utils.DemoHelper;
import com.bibinet.biunion.project.utils.Preferences;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.helpdesk.easeui.UIProvider;

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
        Preferences.init(this);
        DemoHelper.getInstance().init(this);

        ChatClient.Options options = new ChatClient.Options();
        options.setAppkey("1143170710115986#kefuchannelapp44269");//必填项，appkey获取地址：kefu.easemob.com，
        options.setTenantId("44269");//必填项，tenantId获取地址：kefu.easemob.com，“管理员模式 > 设置 > 企业信息”页面的“租户ID”
        // Kefu SDK 初始化
        if (!ChatClient.getInstance().init(this, options)){
            return;
        }
        // Kefu EaseUI的初始化
        UIProvider.getInstance().init(this);
        //后面可以设置其他属性
    }
}
