package com.bibinet.biunion.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.presenter.MoreProjectPresenter;
import com.bibinet.biunion.mvp.view.FragmentHomeView;
import com.bibinet.biunion.mvp.view.MoreProjectView;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.bean.ProjectInfoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-7.
 */

public class MoreProjectActivity extends BaseActivity implements MoreProjectView {
    @BindView(R.id.backImage)
    ImageView backImage;
    @BindView(R.id.searchProject)
    EditText searchProject;
    @BindView(R.id.projectIndustry)
    LinearLayout projectIndustry;
    @BindView(R.id.projectAeara)
    LinearLayout projectAeara;
    @BindView(R.id.projectTime)
    LinearLayout projectTime;
    @BindView(R.id.projectRecyler)
    RecyclerView projectRecyler;
    private MoreProjectPresenter projectPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);
        initView();
        loadData();
    }

    private void loadData() {
        Intent intent=getIntent();
        String selectType = intent.getStringExtra("selectType");
        String detailType=intent.getStringExtra("detailType");
      	switch (Integer.parseInt(selectType)) {
      			case 5:
      				projectPresenter.LoadHomeDataMoreTenderInfo(1,Integer.parseInt(selectType));
      				break;
      			case 6:
                    projectPresenter.LoadHomeDataMoreProjcetInfo(1,Integer.parseInt(selectType));
      				break;
      			case 7:
                    projectPresenter.LoadHomeDataMoreBuyInfo(1,Integer.parseInt(selectType));
      				break;
      			case 8:
                    projectPresenter.LoadHomeDataMoreApplayProjectInfo(1,Integer.parseInt(selectType));
      				break;
      			case 9:
                    projectPresenter.LoadHomeDataMoreProjcetInfo(1,Integer.parseInt(selectType));
      				break;

      			default:
      				break;
      			}
    }
    private void initView() {
         projectPresenter=new MoreProjectPresenter(this);
    }

    @OnClick({R.id.backImage, R.id.projectIndustry, R.id.projectAeara, R.id.projectTime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.backImage:
                finish();
                break;
            case R.id.projectIndustry:

                break;
            case R.id.projectAeara:
                break;
            case R.id.projectTime:
                break;
        }
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onLoadSucess(List<ProjectInfoBean.ItemsBean> projectInfos) {

    }

    @Override
    public void onLoadFailed(String msg) {

    }
}
