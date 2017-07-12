package com.bibinet.biunion.project.ui.fragment;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.presenter.FragmentHomePresenter;
import com.bibinet.biunion.mvp.view.FragmentHomeView;
import com.bibinet.biunion.project.adapter.SocailFooterAdapter;
import com.bibinet.biunion.project.bean.BannerBean;
import com.bibinet.biunion.project.bean.ProjectInfoBean;
import com.bibinet.biunion.project.ui.activity.MoreProjectActivity;
import com.bibinet.biunion.project.ui.activity.PrivatePersonDesignActivity;
import com.bibinet.biunion.project.ui.activity.SearchActivity;
import com.bibinet.biunion.project.ui.activity.SelectCityActivity;
import com.bibinet.biunion.project.utils.ACache;
import com.bibinet.biunion.project.utils.ConvertUtils;
import com.bibinet.biunion.project.utils.HomePopWindowUtils;
import com.bibinet.biunion.project.utils.LoactionUtils;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Homex extends Fragment implements FragmentHomeView, View.OnClickListener {
    Unbinder unbinder;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.projectInfo)
    TextView projectInfo;
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
    @BindView(R.id.swipeReresh)
    SwipeRefreshLayout swipeReresh;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    private View view;
    private LoactionUtils loactionUtils;
    private List<ProjectInfoBean.ItemsBean> projectList = new ArrayList<>();
    private int pageNum = 1;
    private int detailType = 1;
    private FragmentHomePresenter presenter;

    private final int projectInfoType = 5;
    private final int tenderProjectInfoType = 6;
    private final int buyProjectInfoType = 7;
    private final int pProjectInfoType = 8;
    private final int applayProjectInfoType = 9;
    private int selectType = 5;
    private int lastvisibleitem;
    private LinearLayoutManager linearLayoutManager;
    private SocailFooterAdapter adapter;
    private HomePopWindowUtils popWindowUtils;
    private List<BannerBean.ItemBean> bannerInfoList = new ArrayList<>();
    private ConvertUtils convertUtils = new ConvertUtils();
    private ACache aCache;

    public Fragment_Homex() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_homex, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        loadData(false);
        return view;
    }

    private void initView() {
        aCache=ACache.get(getActivity());//初始化aCache
        presenter = new FragmentHomePresenter(this);
        adapter = new SocailFooterAdapter(getActivity(), projectList, bannerInfoList, selectType, detailType);
        projectInfoRecycler.setAdapter(adapter);
        presenter.getBannerData();
        initRecyclerView();
        loactionUtils = new LoactionUtils(getActivity(), location);
        loactionUtils.startLoaction();
        doRefresh();
    }

    public void doRefresh() {
        swipeReresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getBannerData();
                final int nowLocation = convertUtils.areaConvert(location.getText().toString().trim());
                switch (selectType) {
                    case 5:
                        presenter.LoadHomeDataProjcetInfo(1, detailType, nowLocation, false);
                        break;
                    case 6:
                        presenter.LoadHomeDataTenderInfo(1, detailType, nowLocation, false);
                        break;
                    case 7:
                        presenter.LoadHomeDataBuyInfo(1, detailType, nowLocation, false);
                        break;
                    case 8:
                        presenter.LoadHomeDataPProjectInfo(1, detailType, nowLocation, false);
                        break;
                    case 9:
                        presenter.LoadHomeDataApplayProjectInfo(1, detailType, nowLocation, false);
                        break;

                    default:
                        break;
                }
            }
        });
    }

    private void loadData(boolean isLoadMore) {
        if (isLoadMore) {
            adapter.changeMoreStatus(SocailFooterAdapter.LOADING_MORE);
            pageNum++;
        } else {
            pageNum = 1;
        }
        selectDataSource(isLoadMore);
    }

    //选择加载不同的数据类型
    private void selectDataSource(boolean isLoadMore) {
        final int nowLocation = convertUtils.areaConvert(location.getText().toString().trim());
        switch (selectType) {
            case projectInfoType:
                presenter.LoadHomeDataProjcetInfo(pageNum, detailType, nowLocation, isLoadMore);
                break;
            case tenderProjectInfoType:
                presenter.LoadHomeDataTenderInfo(pageNum, detailType, nowLocation, isLoadMore);
                break;
            case buyProjectInfoType:
                presenter.LoadHomeDataBuyInfo(pageNum, detailType, nowLocation, isLoadMore);
                break;
            case pProjectInfoType:
                presenter.LoadHomeDataPProjectInfo(pageNum, detailType, nowLocation, isLoadMore);
                break;
            case applayProjectInfoType:
                presenter.LoadHomeDataApplayProjectInfo(pageNum, detailType, nowLocation, isLoadMore);
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.location, R.id.projectInfo, R.id.projectNameOne, R.id.projectNameTwo, R.id.projectNameThree, R.id.moreProject, R.id.homeSearch, R.id.privateOderingImage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.location:
                Intent intentCityName = new Intent(getActivity(), SelectCityActivity.class);
                startActivityForResult(intentCityName, 1);
                break;
            case R.id.privateOderingImage:
                startActivity(new Intent(getActivity(), PrivatePersonDesignActivity.class));
                break;
            case R.id.projectInfo:
                if (imageView4.isSelected()) {
                	imageView4.setSelected(false);
                		}else {
                    imageView4.setSelected(true);
                }
                selectProjectType();
                break;
            case R.id.projectNameOne:
                detailType = 1;
                if (selectType == 8) {
                    selectType = 5;
                }
                loadData(false);
                break;
            case R.id.projectNameTwo:
                detailType = 2;
                if (selectType == 8) {
                    selectType = 5;
                }
                loadData(false);
                break;
            case R.id.projectNameThree:
                detailType = 3;
                if (selectType == 5) {
                    selectType = 8;
                    loadData(false);
                } else {
                    loadData(false);
                }
                break;
            case R.id.moreProject:
                Intent intent = new Intent(getActivity(), MoreProjectActivity.class);
                intent.putExtra("selectType", String.valueOf(selectType));
                intent.putExtra("detailType", String.valueOf(detailType));
                startActivity(intent);
                break;
            case R.id.homeSearch:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (data != null) {
                String cityName = data.getStringExtra("resultCityNameHot");
                location.setText(cityName);
                loadData(false);
            }
        }
    }

    //弹出选着项目类型对话框
    private void selectProjectType() {
        popWindowUtils = new HomePopWindowUtils(getActivity(), projectInfo);
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

    private void initRecyclerView() {
        projectInfoRecycler.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        projectInfoRecycler.setLayoutManager(linearLayoutManager);
        projectInfoRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastvisibleitem + 1 == adapter.getItemCount()) {
                    loadData(true);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastvisibleitem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 111:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "定位权限开启失败", Toast.LENGTH_SHORT).show();
                } else {
                }
                break;
            case 222:
                if (grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "存储权限开启失败", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void showProgress() {
        swipeReresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeReresh.setRefreshing(false);
    }

    @Override
    public void onLoadSucess(List<ProjectInfoBean.ItemsBean> projectinfoList, boolean isLoadMore) {
        projectList = projectinfoList;
        if (isLoadMore) {
            swipeReresh.setRefreshing(false);
            if (projectList.size() == 0) {
                Toast.makeText(getActivity(), "没有更多数据", Toast.LENGTH_SHORT).show();
                adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
            } else {
                adapter.addMoreItem(projectList);
                adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
            }
        } else {
            adapter = new SocailFooterAdapter(getActivity(), projectList, bannerInfoList, selectType, detailType);
            projectInfoRecycler.setAdapter(adapter);
            swipeReresh.setRefreshing(false);
        }
    }

    @Override
    public void onLoadFaield(String msg) {
        Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
        adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
    }

    @Override
    public void onLoadBannerSucess(BannerBean bannerInfo) {
        if (bannerInfo.getResCode().equals("0000")) {
            Log.i("TAG","bannerchenggong_______________________");
            aCache.put("bannerinfo",bannerInfo,2*ACache.TIME_DAY);
            bannerInfoList = bannerInfo.getItem();
//        		}else{
//            BannerBean bannerBean = (BannerBean) aCache.getAsObject("bannerinfo");
//            bannerInfoList=bannerBean.getItem();
            adapter = new SocailFooterAdapter(getActivity(), projectList, bannerInfoList, selectType, detailType);
            projectInfoRecycler.setAdapter(adapter);
        }

    }

    @Override
    public void onLoadBannerFailed() {
        BannerBean bannerBean = (BannerBean) aCache.getAsObject("bannerinfo");
        bannerInfoList=bannerBean.getItem();
        adapter = new SocailFooterAdapter(getActivity(), projectList, bannerInfoList, selectType, detailType);
        projectInfoRecycler.setAdapter(adapter);
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
                imageView4.setSelected(false);
                popWindowUtils.disMissPopWindow();
                projectInfo.setText(R.string.tenderInfo);
                projectNameOne.setText("招标公告");
                projectNameTwo.setText("中标候选人公示");
                projectNameThree.setText("中标公告");
                selectType = 6;
                loadData(false);
                break;
            case R.id.projectInfo:
                popWindowUtils.disMissPopWindow();
                imageView4.setSelected(false);
                projectInfo.setText(R.string.projectInfo);
                projectNameOne.setText("拟在建项目");
                projectNameTwo.setText("业主委托项目");
                projectNameThree.setText("PPP项目");
                selectType = 5;
                loadData(false);
                break;
            case R.id.buyprojectInfo:
                popWindowUtils.disMissPopWindow();
                imageView4.setSelected(false);
                projectInfo.setText(R.string.buyProjectInfo);
                projectNameOne.setText("政府采购");
                projectNameTwo.setText("企业采购");
                projectNameThree.setText("");
                selectType = 7;
                loadData(false);
                break;
            case R.id.provideProjectInfo:
                popWindowUtils.disMissPopWindow();
                imageView4.setSelected(false);
                projectInfo.setText(R.string.provideProjectInfo);
                projectNameOne.setText("供应商");
                projectNameTwo.setText("采购业主");
                projectNameThree.setText("招标机构");
                selectType = 9;
                loadData(false);
                break;
        }
    }
}
