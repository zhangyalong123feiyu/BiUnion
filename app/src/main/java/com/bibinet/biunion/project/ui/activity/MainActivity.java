package com.bibinet.biunion.project.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.ui.fragment.Fragment_Ask;
import com.bibinet.biunion.project.ui.fragment.Fragment_Home;
import com.bibinet.biunion.project.ui.fragment.Fragment_My;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.bottomhome)
    RelativeLayout bottomhome;
    @BindView(R.id.bottomask)
    RelativeLayout bottomask;
    @BindView(R.id.bottomy)
    RelativeLayout bottomy;
    private Fragment_Home framentHome;
    private Fragment[] fragments = new Fragment[3];
    private Fragment_Ask fragment_Ask;
    private Fragment_My fragment_My;
    private RelativeLayout[] mTabs;
    private int index;
    private int currentTabIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();

    }
    private void initView() {
        framentHome = new Fragment_Home();
        fragment_Ask = new Fragment_Ask();
        fragment_My = new Fragment_My();
        fragments = new Fragment[]{framentHome, fragment_Ask, fragment_My};
        mTabs = new RelativeLayout[]{bottomhome, bottomask, bottomy};
        mTabs[0].setSelected(true);
        getSupportFragmentManager().beginTransaction().add(R.id.fragementcontainer, framentHome).show(framentHome).
                add(R.id.fragementcontainer, fragment_Ask).hide(fragment_Ask).add(R.id.fragementcontainer, fragment_My).hide(fragment_My)
                .commit();
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
}
