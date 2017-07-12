package com.bibinet.biunion.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bibinet.biunion.R;
import com.bibinet.biunion.project.adapter.CityAdapter;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.bean.cityselectbean.BaseIndexPinyinBean;
import com.bibinet.biunion.project.bean.cityselectbean.CityBean;
import com.bibinet.biunion.project.bean.cityselectbean.HeadrBean;
import com.bibinet.biunion.project.decoration.DividerItemDecoration;
import com.bibinet.biunion.project.decoration.SuspensionDecoration;
import com.bibinet.biunion.project.utils.cityselectutil.CommonAdapter;
import com.bibinet.biunion.project.utils.cityselectutil.Constance;
import com.bibinet.biunion.project.utils.cityselectutil.DBManager;
import com.bibinet.biunion.project.utils.cityselectutil.HeaderRecyclerAndFooterWrapperAdapter;
import com.bibinet.biunion.project.utils.cityselectutil.IndexBar;
import com.bibinet.biunion.project.utils.cityselectutil.OnItemClickListener;
import com.bibinet.biunion.project.utils.cityselectutil.SharedPreferencesUtils;
import com.bibinet.biunion.project.utils.cityselectutil.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bibinet on 2017-6-8.
 */

public class SelectCityActivity extends BaseActivity {
    private static final String TAG = "zp";
    private Context mContext;
    private RecyclerView mRv;
    private CityAdapter mAdapter;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private LinearLayoutManager mManager;

    //设置给InexBar、ItemDecoration的完整数据集
    private List<BaseIndexPinyinBean> mSourceDatas;
    //头部数据源
    private List<HeadrBean> mHeaderDatas;
    //主体部分数据源（城市数据）
    private List<CityBean> mBodyDatas;

    private SuspensionDecoration mDecoration;

    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;
    //这是搜索没有内容的界面
    private ViewGroup mEmptyView;
    //搜索框
    private EditText mEtSearch;
    //清除按钮
    private ImageView mIvClear;

    private ListView mListViewResult;
    //数据库操作
    private DBManager mDBManger;
    //返回
    private ImageView mIvBack;
    private AMapLocationClient mAMapLocationClient;


    private String mLocationCity;

    private RelativeLayout mRlHead;

    private List<String> recentCitysList;
private  Intent intent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_citylist);
        //初始化定位
        initLocation();
        initView();
        initDatas();

        mAdapter = new CityAdapter(this, R.layout.city_list_item, mBodyDatas);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
//                String city = mBodyDatas.get(position).getCityName();
//                setIntent(city);
//                pickCity(city);
                intent.putExtra("resultCityNameHot",mBodyDatas.get(position).getCityName());
                setResult(3,intent);
                finish();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                switch (layoutId) {
                    case R.layout.meituan_item_header:
                        final HeadrBean headerBean = (HeadrBean) o;
                        //网格
                        RecyclerView recyclerView = holder.getView(R.id.rvCity);
                        recyclerView.setAdapter(
                                new CommonAdapter<String>(mContext, R.layout.meituan_item_header_item, headerBean.getCityList()) {
                                    @Override
                                    public void convert(ViewHolder holder, final String cityName) {
                                        holder.setText(R.id.tvName, cityName);
                                        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                intent.putExtra("resultCityNameHot",cityName);
                                                setResult(2,intent);
                                                finish();
//                                                setIntent(cityName);
//                                                pickCity(cityName);
                                            }
                                        });
                                    }
                                });
                        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                        break;
                    case R.layout.meituan_item_header_top:
                        holder.setBackgroundColor(R.id.ll_search, 0xffefefef);
                        mEtSearch = (EditText) holder.getConvertView().findViewById(R.id.et_search);
                        mEtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                            @Override
                            public void onFocusChange(View view, boolean b) {
                                if (b) {
                                    Intent intent = new Intent(SelectCityActivity.this, SearchResultActivity.class);
                                    startActivityForResult(intent, Constance.REQUEST_CITY_LIST);
                                }
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        };

        mHeaderAdapter.setHeaderView(0, R.layout.meituan_item_header_top, null);
//        mHeaderAdapter.setHeaderView(1, R.layout.meituan_item_header, mHeaderDatas.get(0));
        mHeaderAdapter.setHeaderView(2, R.layout.meituan_item_header, mHeaderDatas.get(0));
        mHeaderAdapter.setHeaderView(3, R.layout.meituan_item_header, mHeaderDatas.get(1));

        mRv.setAdapter(mHeaderAdapter);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, mSourceDatas)
                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics()))
                .setColorTitleBg(0xffefefef)
                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()))
                .setColorTitleFont(mContext.getResources().getColor(android.R.color.black))
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size()));
        mRv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));


        mIndexBar.setHintTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setLayoutManger(mManager)//设置RecyclerView的LayoutManager
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size())
                .setSourceList(mSourceDatas);


    }

    /**
     * 高德定位
     */
    private void initLocation() {
        mAMapLocationClient = new AMapLocationClient(this);
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(true);
        mAMapLocationClient.setLocationOption(option);
        mAMapLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                    String city = aMapLocation.getCity();
                    if (city.indexOf("市") == city.length() - 1) {
                        city = city.substring(0, city.length() - 1);
                    }
                    HeadrBean header1 = mHeaderDatas.get(0);
                    header1.getCityList().clear();
                    header1.getCityList().add(city);
                    mHeaderAdapter.notifyDataSetChanged();
                }
            }
        });
        mAMapLocationClient.startLocation();
    }

    //A7:47:52:FF:44:97:F6:19:8D:E0:DA:39:1D:0D:0A:21:2D:60:C6:5E
    private void initView() {
        mContext = this;
        mEmptyView = (ViewGroup) findViewById(R.id.empty_view);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));

        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexbar);//IndexBar
        mListViewResult = (ListView) findViewById(R.id.listview_search_result);
        mIvBack = (ImageView) findViewById(R.id.back);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mRlHead = (RelativeLayout) findViewById(R.id.rl_head);
    }

    /**
     * 生成数据源
     *
     * @param
     * @return
     */
    private void initDatas() {
        mDBManger = DBManager.getInstance(this);
        mDBManger.copyDBFile();
        mBodyDatas = mDBManger.getAllCities();
        mSourceDatas = new ArrayList<>();
        mHeaderDatas = new ArrayList<>();

        List<String> locationCityList = new ArrayList<>();
        locationCityList.add("定位中");
        mHeaderDatas.add(new HeadrBean(locationCityList, "定位城市", "定"));

//        recentCitysList = new ArrayList<>();
//        SharedPreferencesUtils.getInstence().getArrayData(mContext, recentCitysList);
//
//        mHeaderDatas.add(new HeadrBean(recentCitysList, "最近访问城市", "近"));
        List<String> hotCitysList = new ArrayList<>();
        hotCitysList.add("上海");
        hotCitysList.add("北京");
        hotCitysList.add("杭州");
        hotCitysList.add("广州");
        mHeaderDatas.add(new HeadrBean(hotCitysList, "热门城市", "热"));
        mSourceDatas.addAll(mHeaderDatas);
        mSourceDatas.addAll(mBodyDatas);


    }


    @Override
    protected void onStart() {
        if (null != mEtSearch) {
            mEtSearch.clearFocus();
            mEtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b) {
//                        Intent intent = new Intent(SelectCityActivity.this, SearchResultActivity.class);
//                        startActivityForResult(intent, Constance.REQUEST_CITY_LIST);
                    }
                }
            });
        }
        super.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constance.REQUEST_CITY_LIST && resultCode == RESULT_OK) {
            if (data != null) {

                String city = data.getStringExtra(Constance.KEY_PICKED_CITY);
//                pickCity(city);
                setIntent(city);


            }
        }
    }

    /**
     * 界面跳转的时候传数据
     *
     * @param city
     */
    private void setIntent(String city) {
        Intent intent = new Intent();
        intent.putExtra(Constance.KEY_PICKED_CITY, city);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * 保存历史城市
     *
     * @param
     */
//    public void pickCity(String pickedCity) {
//
//        if (!recentCitysList.contains(pickedCity)) {
//            if (recentCitysList.size() >= 3) {
//                recentCitysList.remove(0);
//            }
//            if (!"定位中".equals(pickedCity)) {
//                recentCitysList.add(pickedCity);
//            }
//
//            SharedPreferencesUtils.getInstence().putArrayData(mContext, recentCitysList);
//        }
//
//
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAMapLocationClient.stopLocation();
    }
}
