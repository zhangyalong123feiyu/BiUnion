package com.bibinet.biunion.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.presenter.MoreProjectPresenter;
import com.bibinet.biunion.mvp.view.MoreProjectView;
import com.bibinet.biunion.project.adapter.MoreItemAdapter;
import com.bibinet.biunion.project.adapter.MoreProjectAdapter;
import com.bibinet.biunion.project.adapter.SocailFooterAdapter;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.bean.ProjectInfoBean;
import com.bibinet.biunion.project.utils.ConvertUtils;
import com.bibinet.biunion.project.utils.PublicPopWindowUtils;
import com.bibinet.biunion.project.utils.cityselectutil.ConversionMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    TextView searchProject;
    @BindView(R.id.projectIndustry)
    LinearLayout projectIndustry;
    @BindView(R.id.projectAeara)
    LinearLayout projectAeara;
    @BindView(R.id.projectTime)
    LinearLayout projectTime;
    @BindView(R.id.projectRecyler)
    RecyclerView projectRecyler;
    @BindView(R.id.industryTextView)
    TextView industryTextView;
    @BindView(R.id.areaTextView)
    TextView areaTextView;
    @BindView(R.id.timeTextView)
    TextView timeTextView;
    @BindView(R.id.moreSwipeRefrsh)
    SwipeRefreshLayout moreSwipeRefrsh;
    private MoreProjectPresenter projectPresenter;
    private List<String> projectDatas = new ArrayList<>();
    private String[] time = new String[]{"全部", "近一周", "近一个月", "近三个月"};
    private String[] industry = new String[]{"全部", "农、林、牧、渔业", "采矿业", "制造业", "电力、热力、燃气及水生产和供应业", "建筑业",
            "批发和零售业", "交通运输、仓储和邮政业", "住宿和餐饮业", "信息传输、软件和信息技术服务业", "金融业", "房地产业", "租赁和商务服务业", "科学研究和技术服务业", "水利、环境和公共设施管理业",
            "居民服务、修理和其他服务业", "教育", "卫生和社会工作", "文化、体育和娱乐业", "公共管理、社会保障和社会组织", "国际组织"};
    private String[] area = new String[]{"全部", "北京市", "天津市", "河北省", "山西省", "内蒙古自治区", "辽宁省", "吉林省", "黑龙江省", "上海市", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省"
            , "河南省", "湖北省", "湖南省", "广东省", "广西壮族自治区", "重庆市", "四川省", "贵州省", "云南省", "西藏自治区", "陕西省", "甘肃省", "青海省", "宁夏回族自治区", "新疆维吾尔自治区",
            "台湾省", "香港特别行政区", "澳门特别行政区"};
    private String selectText;
    private int pageNumb = 1;
    private String selectType;
    private String detailType;
    private MoreProjectAdapter adapter;
    private boolean isLoadMore;
    private List<ProjectInfoBean.ItemsBean> projectList;
    private int lastvisibleitem=1;
    private LinearLayoutManager linearLayoutManager;
    private ConvertUtils convert=new ConvertUtils();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);
        initView();
        loadData(false);
    }

    private void initView() {
        projectPresenter = new MoreProjectPresenter(this);
        projectRecyler.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(this);
        projectRecyler.setLayoutManager(linearLayoutManager);
        projectRecyler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastvisibleitem + 1 == adapter.getItemCount()) {
                    loadData(true);
                    isLoadMore=true;
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastvisibleitem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });

    }

    //首页过来之后进行数据加载
    private void loadData(boolean isLoadMore) {
        if (isLoadMore) {
            adapter.changeMoreStatus(SocailFooterAdapter.LOADING_MORE);
            pageNumb++;
            Log.i("pageNum","moeragenumber________________________"+pageNumb);
        } else {
            pageNumb = 1;
        }
        Intent intent = getIntent();
        selectType = intent.getStringExtra("selectType");
        detailType = intent.getStringExtra("detailType");
        switch (Integer.parseInt(selectType)) {
            case 5:
                projectPresenter.LoadHomeDataMoreTenderInfo(pageNumb, Integer.parseInt(detailType), 1, "A", 140000);
                break;
            case 6:
                projectPresenter.LoadHomeDataMoreProjcetInfo(pageNumb, Integer.parseInt(detailType), 1, "A", 140000);
                break;
            case 7:
                projectPresenter.LoadHomeDataMoreBuyInfo(pageNumb, Integer.parseInt(detailType), 1, "A", 140000);
                break;
            case 8:
                projectPresenter.LoadHomeDataMoreApplayProjectInfo(pageNumb, Integer.parseInt(detailType), 1, "A", 140000);
                break;
            case 9:
                projectPresenter.LoadHomeDataMoreProjcetInfo(pageNumb, Integer.parseInt(detailType), 1, "A", 140000);
                break;

            default:
                break;
        }
    }

    @OnClick({R.id.searchProject,R.id.backImage, R.id.projectIndustry, R.id.projectAeara, R.id.projectTime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.searchProject:
                startActivity(new Intent(this,SearchActivity.class));
                break;
            case R.id.backImage:
                finish();
                break;
            case R.id.projectIndustry:
                projectDatas = Arrays.asList(industry);
                initPopView(projectDatas,industryTextView);
                break;
            case R.id.projectAeara:
                projectDatas = Arrays.asList(area);
                initPopView(projectDatas, areaTextView);
                break;
            case R.id.projectTime:
                projectDatas = Arrays.asList(time);
                initPopView(projectDatas, timeTextView);
                break;
        }
    }

    //开始进行查询
    private void initPopView(List<String> projectDatas, final TextView textView) {
        final PublicPopWindowUtils publicPopWindowUtils = new PublicPopWindowUtils(this);
        publicPopWindowUtils.showPopWindow(R.layout.item_popwindow);
        View view = publicPopWindowUtils.getPopview();
        RecyclerView popViewRecyclerView = (RecyclerView) view.findViewById(R.id.popRecyclerView);
        MoreItemAdapter adapter = new MoreItemAdapter(projectDatas, this);
        popViewRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        popViewRecyclerView.setAdapter(adapter);
        adapter.setOnclickListioner(new MoreItemAdapter.ProjectTextClickListioner() {
            @Override
            public void onProjectTextClickLitioner(View view) {
                selectText = ((TextView) view).getText().toString();
                publicPopWindowUtils.dissMisPopWindow();
                textView.setText(selectText);
                Log.i("TAG",""+industryTextView.getText().toString());
                doSelect(String.valueOf(convert.timeConvert(timeTextView.getText().toString())),String.valueOf(convert.industryConvert(industryTextView.getText().toString())),String.valueOf(convert.areaConvert(areaTextView.getText().toString())));
            }
        });
        publicPopWindowUtils.showPopWindow(textView);
    }

    private void doSelect(String time, String industry, String ar) {
        switch (Integer.parseInt(selectType)) {
            case 5:
                projectPresenter.LoadHomeDataMoreTenderInfo(pageNumb, Integer.parseInt(detailType), Integer.parseInt(time), industry, Integer.parseInt(ar));
                break;
                case 6:
                    projectPresenter.LoadHomeDataMoreTenderInfo(pageNumb, Integer.parseInt(detailType), Integer.parseInt(time), industry, Integer.parseInt(ar));
                    break;
                case 7:
                    projectPresenter.LoadHomeDataMoreBuyInfo(pageNumb, Integer.parseInt(detailType), Integer.parseInt(time), industry, Integer.parseInt(ar));
                    break;
                case 8:
                    projectPresenter.LoadHomeDataMorePProjectInfo(pageNumb, Integer.parseInt(detailType), Integer.parseInt(time), industry, Integer.parseInt(ar));
                    break;
                case 9:
                    projectPresenter.LoadHomeDataMoreApplayProjectInfo(pageNumb, Integer.parseInt(detailType), Integer.parseInt(time), industry, Integer.parseInt(ar));
                    break;
        }
    }

    @Override
    public void showProgress() {
       moreSwipeRefrsh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        moreSwipeRefrsh.setRefreshing(false);
    }

    @Override
    public void onLoadSucess(List<ProjectInfoBean.ItemsBean> projectInfos) {
        if (projectInfos.size() == 0) {
            Toast.makeText(this, "没有跟多数据了", Toast.LENGTH_SHORT).show();
            adapter.changeMoreStatus(SocailFooterAdapter.LOAD_NODATA);
        }
        projectList = projectInfos;
        if (isLoadMore) {
            if (projectInfos.size() == 0) {
                moreSwipeRefrsh.setRefreshing(false);
                adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
                // infoListView.scrollToPosition(InfoRefreshFootAdapter.Lastposition);
                projectRecyler.smoothScrollToPosition(adapter.getItemCount() - 1);
            } else {
                adapter.addMoreItem(projectList);
                adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
            }
        } else {
            adapter = new MoreProjectAdapter(this, projectInfos);
            projectRecyler.setAdapter(adapter);
            moreSwipeRefrsh.setRefreshing(false);
        }
    }

    @Override
    public void onLoadFailed(String msg) {
    }
}
