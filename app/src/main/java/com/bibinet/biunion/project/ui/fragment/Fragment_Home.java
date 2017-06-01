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

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.view.FragmentHomeView;
import com.bibinet.biunion.project.adapter.ProjectInfoAdapter;
import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.bean.ProjectInfoBean;
import com.bibinet.biunion.project.builder.MyViewPager;
import com.bibinet.biunion.project.ui.activity.PrivatePersonDesignActivity;
import com.bibinet.biunion.project.utils.BannerUtils;
import com.bibinet.biunion.project.utils.LoactionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
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
    //    @BindView(R.id.projectNameOne)
//    TextView projectNameOne;
//    @BindView(R.id.projectNameTwo)
//    TextView projectNameTwo;
//    @BindView(R.id.projectNameThree)
//    TextView projectNameThree;
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
    private View view;
    private LoactionUtils loactionUtils;
    private List<ProjectInfoBean> projectList = new ArrayList<>();

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
        return view;
    }

    private void initView() {
        loactionUtils = new LoactionUtils(getActivity(), location);
        loactionUtils.startLoaction();
        BannerUtils bannerUtils = new BannerUtils(getActivity(), viewpager, groupContain, Arrays.asList(Constants.ImageUrls));
        bannerUtils.startPlayBanner();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        loactionUtils.destroyLocation();
    }
    @OnClick({R.id.location, R.id.projectInfo, R.id.projectNameOne, R.id.projectNameTwo, R.id.projectNameThree, R.id.moreProject})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.location:
                break;
            case R.id.projectInfo:
                break;
            case R.id.projectNameOne:
                projectNameOne.setChecked(true);
//                projectNameOne.setSelected(false);
//                projectNameTwo.setSelected(true);
//                projectNameThree.setSelected(false);
                initData();
                break;
            case R.id.projectNameTwo:
//                projectNameOne.setSelected(true);
//                projectNameTwo.setSelected(false);
//                projectNameThree.setSelected(false);
                initData();
                break;
            case R.id.projectNameThree:
//                projectNameOne.setSelected(false);
//                projectNameTwo.setSelected(false);
//                projectNameThree.setSelected(true);
                initData();
                break;
            case R.id.moreProject:
                startActivity(new Intent(getActivity(), PrivatePersonDesignActivity.class));
                break;
        }
    }

    private void initData() {
        projectList.add(new ProjectInfoBean("山西比比", "非常非常好", "太原", "还有3天"));
        projectList.add(new ProjectInfoBean("山西比比", "非常非常好", "太原", "还有3天"));
        projectList.add(new ProjectInfoBean("山西比比", "非常非常好", "太原", "还有3天"));
        projectList.add(new ProjectInfoBean("山西比比", "非常非常好", "太原", "还有3天"));
        projectList.add(new ProjectInfoBean("山西比比", "非常非常好", "太原", "还有3天"));
        projectInfoRecycler.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        ProjectInfoAdapter adapter = new ProjectInfoAdapter(getActivity(), projectList);
        projectInfoRecycler.setAdapter(adapter);
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
}
