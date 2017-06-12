package com.bibinet.biunion.project.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.presenter.FragmentHomePresenter;
import com.bibinet.biunion.mvp.view.FragmentHomeView;
import com.bibinet.biunion.project.adapter.ProjectInfoAdapterx;
import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.bean.ProjectInfoBean;
import com.bibinet.biunion.project.builder.MyViewPager;
import com.bibinet.biunion.project.ui.activity.MoreProjectActivity;
import com.bibinet.biunion.project.ui.activity.PrivatePersonDesignActivity;
import com.bibinet.biunion.project.ui.activity.SearchActivity;
import com.bibinet.biunion.project.ui.activity.SelectCityActivity;
import com.bibinet.biunion.project.utils.BannerUtils;
import com.bibinet.biunion.project.utils.LoactionUtils;
import com.bibinet.biunion.project.utils.HomePopWindowUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Home extends Fragment implements FragmentHomeView,View.OnClickListener {
    Unbinder unbinder;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.projectInfo)
    TextView projectInfo;
    @BindView(R.id.viewpager)
    MyViewPager viewpager;
    @BindView(R.id.group_contain)
    LinearLayout groupContain;
    @BindView(R.id.projectInfoRecycler)
    RecyclerView projectInfoRecycler;
    @BindView(R.id.projectNameOne)
    RadioButton projectNameOne;
    @BindView(R.id.projectNameTwo)
    RadioButton projectNameTwo;
    @BindView(R.id.projectNameThree)
    RadioButton projectNameThree;
    @BindView(R.id.moreProject)
    ImageView moreProject;
    @BindView(R.id.xRereshView)
    XRefreshView xRereshView;
    @BindView(R.id.homeSearch)
    LinearLayout homeSearch;
    @BindView(R.id.privateOderingImage)
    ImageView privateOderingImage;
    @BindView(R.id.projectNameOneBottomLine)
    View projectNameOneBottomLine;
    @BindView(R.id.projectNameTwoBottomLine)
    View projectNameTwoBottomLine;
    @BindView(R.id.projectNameThreeBottomLine)
    View projectNameThreeBottomLine;
    private View view;
    private LoactionUtils loactionUtils;
    private List<ProjectInfoBean.ItemsBean> projectList = new ArrayList<>();
    private ProjectInfoAdapterx adapter;
    private int pageNum=1;
    private int detailType=1;
    private FragmentHomePresenter presenter;

    private int projectInfoType=5;
    private int tenderProjectInfoType=6;
    private static int buyProjectInfoType=7;
    private static int pProjectInfoType=8;
    private static int applayProjectInfoType=9;
    private int selectType=5;
    private ProjectInfoAdapterx adapterx;

    public Fragment_Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        loadData();
        return view;
    }

    private void loadData() {
        presenter = new FragmentHomePresenter(this);
        presenter.LoadHomeDataProjcetInfo(pageNum,1);
    }

    private void initView() {
        projectInfoRecycler.setHasFixedSize(true);
        initData();
        adapter = new ProjectInfoAdapterx(getActivity(), projectList);
        // 设置静默加载模式
//        xRefreshView1.setSilenceLoadMore();
        // 静默加载模式不能设置footerview
        projectInfoRecycler.setAdapter(adapter);
        //设置刷新完成以后，headerview固定的时间
        xRereshView.setPinnedTime(1000);
        xRereshView.setMoveForHorizontal(true);
        xRereshView.setPullLoadEnable(true);
        xRereshView.setAutoLoadMore(false);
        xRereshView.enableReleaseToLoadMore(true);
        xRereshView.enableRecyclerViewPullUp(true);
        xRereshView.enablePullUpWhenLoadCompleted(true);
        xRereshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                projectInfoRecycler.setAdapter(adapter);
                xRereshView.stopRefresh();
                presenter.LoadHomeDataProjcetInfo(pageNum,1);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                pageNum++;
              	switch (selectType) {
              			case 5:
              				presenter.LoadHomeDataProjcetInfo(pageNum,detailType);
              				break;
              			case 6:
                            presenter.LoadHomeDataTenderInfo(pageNum,detailType);
              				break;
              			case 7:
                            presenter.LoadHomeDataBuyInfo(pageNum,detailType);
              				break;
              			case 8:
                            presenter.LoadHomeDataPProjectInfo(pageNum,detailType);
              				break;
              			case 9:
                            presenter.LoadHomeDataApplayProjectInfo(pageNum,detailType);
              				break;
              			default:
              				break;
              			}
                xRereshView.stopLoadMore();
            }
        });
        loactionUtils = new LoactionUtils(getActivity(), location);
        loactionUtils.startLoaction();
        BannerUtils bannerUtils = new BannerUtils(getActivity(), viewpager, groupContain, Arrays.asList(Constants.ImageUrls));
        bannerUtils.startPlayBanner();
    }

    @OnClick({R.id.location, R.id.projectInfo, R.id.projectNameOne, R.id.projectNameTwo, R.id.projectNameThree, R.id.moreProject, R.id.homeSearch,R.id.privateOderingImage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.location:
                startActivity(new Intent(getActivity(), SelectCityActivity.class));
                break;
            case R.id.privateOderingImage:
                startActivity(new Intent(getActivity(), PrivatePersonDesignActivity.class));
                break;
            case R.id.projectInfo:
                selectProjectType();
                break;
            case R.id.projectNameOne:
                detailType=1;
                break;
            case R.id.projectNameTwo:
                selectType=2;
                break;
            case R.id.projectNameThree:
                selectType=3;
                break;
            case R.id.moreProject:
                startActivity(new Intent(getActivity(), MoreProjectActivity.class));
                break;
            case R.id.homeSearch:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
        }
    }

    private void selectProjectType() {
        HomePopWindowUtils popWindowUtils = new HomePopWindowUtils(getActivity(), projectInfo);
        popWindowUtils.showPopWindow();
        View popview = popWindowUtils.getPopView();
        TextView popwProjectInfo = (TextView) popview.findViewById(R.id.projectInfo);
        TextView projectTenderInfo = (TextView) popview.findViewById(R.id.tenderInfo);
        TextView projectBuyInfo = (TextView) popview.findViewById(R.id.buyprojectInfo);
        TextView projectProvideInfo = (TextView) popview.findViewById(R.id.provideProjectInfo);
        popwProjectInfo.setOnClickListener(this);
        projectTenderInfo.setOnClickListener(this);
        projectBuyInfo.setOnClickListener(this);
        projectProvideInfo.setOnClickListener(this);
    }


    private void initData() {
        projectInfoRecycler.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onLoadSucess(List<ProjectInfoBean.ItemsBean> projectinfoList) {
        xRereshView.stopRefresh();
        projectList=projectinfoList;
        if (adapterx==null) {
            adapterx=new ProjectInfoAdapterx(getActivity(),projectinfoList);
        }else {
            adapterx.notifyDataSetChanged();
        }
        projectInfoRecycler.setAdapter(adapterx);

    }

    @Override
    public void onLoadFaield(String msg) {

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        loactionUtils.destroyLocation();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tenderInfo:
                projectInfo.setText(R.string.tenderInfo);
                projectNameOne.setText("招标公告");
                projectNameTwo.setText("中标候选人公示");
                projectNameThree.setText("中标公告");
                presenter.LoadHomeDataTenderInfo(pageNum,detailType);
                break;
            case R.id.projectInfo:
                projectInfo.setText(R.string.projectInfo);
                projectNameOne.setText("拟在建项目");
                projectNameTwo.setText("业主委托项目");
                projectNameThree.setText("PPP项目");
                presenter.LoadHomeDataProjcetInfo(pageNum,detailType);
                break;
            case R.id.buyprojectInfo:
                projectInfo.setText(R.string.buyProjectInfo);
                projectNameOne.setText("政府采购");
                projectNameTwo.setText("企业采购");
                projectNameThree.setText("");
                presenter.LoadHomeDataBuyInfo(pageNum,detailType);
                break;
            case R.id.provideProjectInfo:
                projectInfo.setText(R.string.provideProjectInfo);
                projectNameOne.setText("供应商");
                projectNameTwo.setText("采购业主");
                projectNameThree.setText("招标机构");
                presenter.LoadHomeDataApplayProjectInfo(pageNum,detailType);
                break;
        }
    }
}
