package com.bibinet.biunion.project.ui.activity;

import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.builder.JPushReciver;
import com.bibinet.biunion.project.ui.fragment.Fragment_Ask;
import com.bibinet.biunion.project.ui.fragment.Fragment_Focus;
import com.bibinet.biunion.project.ui.fragment.Fragment_Home;
import com.bibinet.biunion.project.ui.fragment.Fragment_My;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bibinet.biunion.project.builder.JPushReciver.MESSAGE_RECEIVED_ACTION;

public class MainActivity extends BaseActivity {
    @BindView(R.id.bottomhome)
    RelativeLayout bottomhome;
    @BindView(R.id.bottomFoucs)
    RelativeLayout bottomFoucs;
    @BindView(R.id.bottomask)
    RelativeLayout bottomask;
    @BindView(R.id.bottomy)
    RelativeLayout bottomy;
    @BindView(R.id.texthome)
    TextView texthome;
    private Fragment_Home framentHome;
    private Fragment[] fragments = new Fragment[4];
    private Fragment_Ask fragment_Ask;
    private Fragment_My fragment_My;
    private Fragment_Focus fragment_Focus;

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
        StatusBarUtil.setColor(this, Color.argb(255,240,196,51),0);
        registerMessageReceiver();
    }
    private void initView() {
        framentHome = new Fragment_Home();
        fragment_Ask = new Fragment_Ask();
        fragment_My = new Fragment_My();
        fragment_Focus = new Fragment_Focus();
        fragments = new Fragment[]{framentHome,fragment_Focus,fragment_Ask, fragment_My};
        mTabs = new RelativeLayout[]{bottomhome, bottomFoucs,bottomask, bottomy};
        mTabs[0].setSelected(true);
        getSupportFragmentManager().beginTransaction().add(R.id.fragementcontainer, framentHome).show(framentHome).
                add(R.id.fragementcontainer, fragment_Focus).hide(fragment_Focus).
                add(R.id.fragementcontainer, fragment_Ask).hide(fragment_Ask).add(R.id.fragementcontainer, fragment_My).hide(fragment_My)
                .commit();
    }
    public void registerMessageReceiver() {
        JPushReciver jPushReciver = new JPushReciver(texthome);
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(jPushReciver, filter);
    }
    @OnClick({R.id.bottomhome, R.id.bottomask, R.id.bottomy,R.id.bottomFoucs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bottomhome:
                index = 0;
                break;
            case R.id.bottomFoucs:
                index = 1;
                break;
            case R.id.bottomask:
                index = 2;
                break;
            case R.id.bottomy:
                index = 3;
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
