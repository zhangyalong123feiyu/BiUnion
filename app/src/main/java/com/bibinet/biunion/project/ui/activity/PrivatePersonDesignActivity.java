package com.bibinet.biunion.project.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.ui.fragment.Fragment_BuyInfo;
import com.bibinet.biunion.project.ui.fragment.Fragment_ProjectInfo;
import com.bibinet.biunion.project.ui.fragment.Fragment_TederInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-1.
 */

public class PrivatePersonDesignActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.projectInfo)
    TextView projectInfo;
    @BindView(R.id.tenderProjectInfo)
    TextView tenderProjectInfo;
    @BindView(R.id.buyProjectInfo)
    TextView buyProjectInfo;
    @BindView(R.id.projectInfoView)
    View projectInfoView;
    @BindView(R.id.tenderProjectInfoView)
    View tenderProjectInfoView;
    @BindView(R.id.buyProjectInfoView)
    View buyProjectInfoView;
    @BindView(R.id.privateFragmentContainer)
    RelativeLayout privateFragmentContainer;

    private Fragment[] fragements=new Fragment[3];
    private TextView[] textViews=new TextView[3];
    private int index;
    private int currentTabIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("信息订阅");
        titleImageleft.setVisibility(View.VISIBLE);

        fragements[0]=new Fragment_ProjectInfo();
        fragements[1]=new Fragment_TederInfo();
        fragements[2]=new Fragment_BuyInfo();
        textViews=new TextView[]{projectInfo,tenderProjectInfo,buyProjectInfo};
        textViews[0].setSelected(true);
        getSupportFragmentManager().beginTransaction().add(R.id.privateFragmentContainer, fragements[0]).show(fragements[0]).
                add(R.id.privateFragmentContainer, fragements[1]).hide(fragements[1]).
                add(R.id.privateFragmentContainer, fragements[2]).hide(fragements[2])
                .commit();
    }

    @OnClick({R.id.title_imageleft, R.id.projectInfo, R.id.tenderProjectInfo, R.id.buyProjectInfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_imageleft:
                finish();
                break;
            case R.id.projectInfo:
                index = 0;
                break;
            case R.id.tenderProjectInfo:
                index = 1;
                break;
            case R.id.buyProjectInfo:
                index = 2;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragements[currentTabIndex]);
            if (!fragements[index].isAdded()) {
                trx.add(R.id.fragementcontainer, fragements[index]);
            }
            trx.show(fragements[index]).commit();
        }
        textViews[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        textViews[index].setSelected(true);
        currentTabIndex = index;
    }
}
