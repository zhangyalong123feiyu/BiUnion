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
import com.bibinet.biunion.project.ui.activity.PrivatePersonDesignActivity;
import com.bibinet.biunion.project.ui.activity.SearchActivity;
import com.bibinet.biunion.project.utils.BannerUtils;
import com.bibinet.biunion.project.utils.LoactionUtils;
import com.bibinet.biunion.project.utils.PopWindowUtils;

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
public class Fragment_Home extends Fragment implements FragmentHomeView {
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
    private View view;
    private LoactionUtils loactionUtils;
    private List<ProjectInfoBean> projectList = new ArrayList<>();
    private ProjectInfoAdapterx adapter;

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
        FragmentHomePresenter presenter = new FragmentHomePresenter(this);
        presenter.LoadHomeData();
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
//        xRereshView.setPinnedTime(1000);
//        xRereshView.setMoveForHorizontal(true);
//        xRereshView.setPullLoadEnable(true);
//        xRereshView.setAutoLoadMore(false);
//       // adapter.setCustomLoadMoreView(new XRefreshViewFooter(getActivity()));
//        xRereshView.enableReleaseToLoadMore(false);
//        xRereshView.enableRecyclerViewPullUp(true);
//        xRereshView.enablePullUpWhenLoadCompleted(true);
        xRereshView.setPullLoadEnable(true);
        xRereshView.setAutoLoadMore(false);
        xRereshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
            }
        });
        loactionUtils = new LoactionUtils(getActivity(), location);
        loactionUtils.startLoaction();
        BannerUtils bannerUtils = new BannerUtils(getActivity(), viewpager, groupContain, Arrays.asList(Constants.ImageUrls));
        bannerUtils.startPlayBanner();
    }

    @OnClick({R.id.location, R.id.projectInfo, R.id.projectNameOne, R.id.projectNameTwo, R.id.projectNameThree, R.id.moreProject,R.id.homeSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.location:
                break;
            case R.id.projectInfo:
                selectProjectType();
                break;
            case R.id.projectNameOne:
                initData();
                break;
            case R.id.projectNameTwo:
                initData();
                break;
            case R.id.projectNameThree:
                initData();
                break;
            case R.id.moreProject:
                startActivity(new Intent(getActivity(), PrivatePersonDesignActivity.class));
                break;
            case R.id.homeSearch:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
        }
    }

    private void selectProjectType() {
        PopWindowUtils popWindowUtils = new PopWindowUtils(getActivity(), projectInfo, projectNameOne, projectNameTwo, projectNameThree);
        popWindowUtils.showPopWindow();
    }

    private void initData() {
        projectList.add(new ProjectInfoBean("山西比比", "非常非常好", "太原", "还有3天", ""));
        projectList.add(new ProjectInfoBean("山西比比", "非常非常好", "太原", "还有3天", ""));
        projectList.add(new ProjectInfoBean("山西比比", "非常非常好", "太原", "还有3天", ""));
        projectList.add(new ProjectInfoBean("山西比比", "非常非常好", "太原", "还有3天", ""));
        projectList.add(new ProjectInfoBean("山西比比", "非常非常好", "太原", "还有3天", ""));
        projectInfoRecycler.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
//        ProjectInfoAdapter adapter = new ProjectInfoAdapter(getActivity(), projectList);
//        projectInfoRecycler.setAdapter(adapter);
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onLoadSucess() {

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

}
