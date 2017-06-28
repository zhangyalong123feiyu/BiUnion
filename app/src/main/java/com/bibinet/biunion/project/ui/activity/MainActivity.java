package com.bibinet.biunion.project.ui.activity;

import android.Manifest;
import android.app.Notification;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.bean.LoginResultBean;
import com.bibinet.biunion.project.builder.JPushReciver;
import com.bibinet.biunion.project.ui.fragment.Fragment_Ask;
//import com.bibinet.biunion.project.ui.fragment.Fragment_Home;
import com.bibinet.biunion.project.ui.fragment.Fragment_Homex;
import com.bibinet.biunion.project.ui.fragment.Fragment_My;
import com.bibinet.biunion.project.utils.SharedPresUtils;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

public class MainActivity extends BaseActivity {
    @BindView(R.id.bottomhome)
    RelativeLayout bottomhome;
//    @BindView(R.id.bottomFoucs)
//    RelativeLayout bottomFoucs;
    @BindView(R.id.bottomask)
    RelativeLayout bottomask;
    @BindView(R.id.bottomy)
    RelativeLayout bottomy;
    @BindView(R.id.texthome)
    TextView texthome;
    private Fragment_Homex framentHome;
    private Fragment[] fragments = new Fragment[3];
    private Fragment_Ask fragment_Ask;
    private Fragment_My fragment_My;

    private RelativeLayout[] mTabs;
    private int index;
    private int currentTabIndex=0;

    private JPushReciver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public static boolean isForeground = false;
    private long mPressedTime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
//        StatusBarUtil.setColor(this, Color.argb(255,240,196,51),0);
        registerMessageReceiver();
    }
    private void initView() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            // 定位所需权限
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},111);
            }else {
            }
            //写入存储卡权限
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},222);
            }else {

            }
        }

        SharedPresUtils sharedPresUtils=SharedPresUtils.getsSharedPresUtils(this);
        String loginString = sharedPresUtils.getString("loginResultData", null);
        Gson gson=new Gson();
        Constants.loginresultInfo=gson.fromJson(loginString, LoginResultBean.class);
        framentHome = new Fragment_Homex();
        fragment_Ask = new Fragment_Ask();
        fragment_My = new Fragment_My();
        fragments = new Fragment[]{framentHome,fragment_Ask, fragment_My};
        mTabs = new RelativeLayout[]{bottomhome,bottomask, bottomy};
        mTabs[0].setSelected(true);
        getSupportFragmentManager().beginTransaction().add(R.id.fragementcontainer, framentHome).show(framentHome).
                add(R.id.fragementcontainer, fragment_Ask).hide(fragment_Ask).add(R.id.fragementcontainer, fragment_My).hide(fragment_My)
                .commit();
    }
    public void registerMessageReceiver() {
        setStyleBasic();
        JPushReciver jPushReciver = new JPushReciver(texthome);
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(jPushReciver, filter);
    }
    @OnClick({R.id.bottomhome, R.id.bottomask, R.id.bottomy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bottomhome:
                index = 0;
                break;
            case R.id.bottomask:
                index = 1;
                break;
            case R.id.bottomy:
                index = 2;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragementcontainer, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }
    /**
     * 设置通知提示方式 - 基础属性
     */
    private void setStyleBasic() {
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(this);
        builder.statusBarDrawable = R.mipmap.search;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为点击后自动消失
        builder.notificationDefaults = Notification.DEFAULT_SOUND;  //设置为铃声（ Notification.DEFAULT_SOUND）或者震动（ Notification.DEFAULT_VIBRATE）
        JPushInterface.setPushNotificationBuilder(1, builder);
    }

    //双击退出程序
    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if ((mNowTime - mPressedTime) > 2000) {//比较两次按键时间差
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        } else {//退出程序
            this.finish();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
