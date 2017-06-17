package com.bibinet.biunion.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.presenter.MoreProjectPresenter;
import com.bibinet.biunion.mvp.view.MoreProjectView;
import com.bibinet.biunion.project.adapter.MoreItemAdapter;
import com.bibinet.biunion.project.adapter.MoreProjectAdapter;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.bean.ProjectInfoBean;
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
    EditText searchProject;
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
    private MoreProjectPresenter projectPresenter;
    private List<String> projectDatas = new ArrayList<>();
    private String[] time = new String[]{"全部", "1", "2", "3"};
    private String[] industry = new String[]{"全部", "农、林、牧、渔业", "采矿业", "制造业","电力、热力、燃气及水生产和供应业","建筑业",
            "批发和零售业","交通运输、仓储和邮政业","住宿和餐饮业","信息传输、软件和信息技术服务业","金融业","房地产业","租赁和商务服务业","科学研究和技术服务业","水利、环境和公共设施管理业",
            "居民服务、修理和其他服务业","教育","卫生和社会工作","文化、体育和娱乐业","公共管理、社会保障和社会组织","国际组织"};
    private String[] area = new String[]{"全部", "北京市", "天津市", "河北省","山西省","内蒙古自治区","辽宁省","吉林省","黑龙江省","上海市","江苏省","浙江省","安徽省","福建省","江西省","山东省"
            ,"河南省","湖北省","湖南省","广东省","广西壮族自治区","重庆市","四川省","贵州省","云南省","西藏自治区","陕西省","甘肃省","青海省","宁夏回族自治区","新疆维吾尔自治区",
            "台湾省","香港特别行政区","澳门特别行政区"};
    private String selectText;
    private int pageNumb=1;
    private String selectType;
    private String detailType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);
        initView();
        loadData();
    }
//首页过来之后进行数据加载
    private void loadData() {
        Intent intent = getIntent();
         selectType = intent.getStringExtra("selectType");
         detailType = intent.getStringExtra("detailType");
        switch (Integer.parseInt(selectType)) {
            case 5:
                projectPresenter.LoadHomeDataMoreTenderInfo(pageNumb,Integer.parseInt(selectType),1,"",1);
                break;
            case 6:
                projectPresenter.LoadHomeDataMoreProjcetInfo(1, Integer.parseInt(selectType),1,"",1);
                break;
            case 7:
                projectPresenter.LoadHomeDataMoreBuyInfo(1, Integer.parseInt(selectType),1,"",1);
                break;
            case 8:
                projectPresenter.LoadHomeDataMoreApplayProjectInfo(1, Integer.parseInt(selectType),1,"",1);
                break;
            case 9:
                projectPresenter.LoadHomeDataMoreProjcetInfo(1, Integer.parseInt(selectType),1,"",1);
                break;

            default:
                break;
        }
    }

    private void initView() {
        projectPresenter = new MoreProjectPresenter(this);
        ConversionMap conversionMap=new ConversionMap();
        conversionMap.getAllMap();
        Map<String, String> areaMap = conversionMap.getAreaMap();
        Map<String, String> industryMap = conversionMap.getIndustryMap();
        Map<String, String> timeMap = conversionMap.getTimeMap();

    }

    @OnClick({R.id.backImage, R.id.projectIndustry, R.id.projectAeara, R.id.projectTime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.backImage:
                finish();
                break;
            case R.id.projectIndustry:
                projectDatas = Arrays.asList(industry);
                initPopView(projectDatas,industryTextView);
                doSelect(industryTextView.getText().toString(),timeTextView.getText().toString(),areaTextView.getText().toString());
                break;
            case R.id.projectAeara:
                projectDatas = Arrays.asList(area);
                initPopView(projectDatas,areaTextView);
                break;
            case R.id.projectTime:
                projectDatas = Arrays.asList(time);
                initPopView(projectDatas,timeTextView);
                break;
        }
    }

    private void doSelect(String time, String date, String ar) {
        	switch (Integer.parseInt(selectType)) {
                case 5:
                    projectPresenter.LoadHomeDataMoreTenderInfo(pageNumb, Integer.parseInt(selectType), Integer.parseInt(time), date, Integer.parseInt(ar));
                    break;
                case 6:
                    projectPresenter.LoadHomeDataMoreProjcetInfo(1, Integer.parseInt(selectType), Integer.parseInt(time), date, Integer.parseInt(ar));
                    break;
                case 7:
                    projectPresenter.LoadHomeDataMoreBuyInfo(1, Integer.parseInt(selectType),Integer.parseInt(time), date, Integer.parseInt(ar));
                    break;
                case 8:
                    projectPresenter.LoadHomeDataMoreApplayProjectInfo(1, Integer.parseInt(selectType),Integer.parseInt(time), date, Integer.parseInt(ar));
                    break;
                case 9:
                    projectPresenter.LoadHomeDataMoreProjcetInfo(1, Integer.parseInt(selectType),Integer.parseInt(time), date, Integer.parseInt(ar));
                    break;
            }
            }

    //开始进行查询
    private void initPopView(List<String> projectDatas, final TextView textView) {
        final PublicPopWindowUtils publicPopWindowUtils = new PublicPopWindowUtils(this);
        publicPopWindowUtils.showPopWindow(R.layout.item_popwindow);
        View view=publicPopWindowUtils.getPopview();
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
            }
        });
        publicPopWindowUtils.showPopWindow(textView);
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onLoadSucess(List<ProjectInfoBean.ItemsBean> projectInfos) {
        projectRecyler.setLayoutManager(new LinearLayoutManager(this));
        MoreProjectAdapter adapter=new MoreProjectAdapter(this,projectInfos);
        projectRecyler.setAdapter(adapter);
    }

    @Override
    public void onLoadFailed(String msg) {
    Log.i("TAG","more_error---------------------------"+msg);
    }
}
