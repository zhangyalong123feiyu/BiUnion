package com.bibinet.biunion.project.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;


import com.bibinet.biunion.R;
import com.bibinet.biunion.project.adapter.ResultListAdapter;
import com.bibinet.biunion.project.bean.cityselectbean.CityBean;
import com.bibinet.biunion.project.utils.cityselectutil.Constance;
import com.bibinet.biunion.project.utils.cityselectutil.DBManager;

import java.util.ArrayList;
import java.util.List;


/**
 * 城市搜索界面
 * Created by zp on 2016/12/16.
 */

public class SearchResultActivity extends Activity implements View.OnClickListener {
    private EditText mEtSearch;
    private ListView mLvSearch;
    private ViewGroup mEmptyView;
    private ImageView mIvClear;
    private Button mBtnBack;
    private DBManager mDBManger;
    private ResultListAdapter mResultAdapter;
    private List<CityBean> mCityBody;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mDBManger = DBManager.getInstance(this);
        mCityBody = mDBManger.getAllCities();
        initView();
    }

    private void initView() {
        mIvClear = (ImageView) findViewById(R.id.iv_search_clear);
        mEmptyView = (ViewGroup) findViewById(R.id.empty_view);
        mEtSearch = (EditText) findViewById(R.id.et_search);
        mLvSearch = (ListView) findViewById(R.id.lv_search);
        mBtnBack = (Button) findViewById(R.id.btn_back);
        mResultAdapter = new ResultListAdapter(this, null);
        mLvSearch.setAdapter(mResultAdapter);
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String keyword = editable.toString();
                List<CityBean> resultCity = new ArrayList<CityBean>();
                if (TextUtils.isEmpty(editable.toString())) {
                    mEmptyView.setVisibility(View.GONE);
                    mLvSearch.setVisibility(View.GONE);
                    mIvClear.setVisibility(View.GONE);

                } else {
                    mIvClear.setVisibility(View.VISIBLE);
                    mLvSearch.setVisibility(View.VISIBLE);
                    for (CityBean cityBean : mCityBody) {
                        String name = cityBean.getCityName();
                        String pinyin = cityBean.getCityPinyin();
                        String headTag=cityBean.getHeadTag();
                        if (name.indexOf(keyword) != -1 || pinyin.toUpperCase().startsWith(keyword.toUpperCase())||headTag.equals(keyword.toUpperCase())) {
                            resultCity.add(cityBean);
                        }
                    }
                    if (null == resultCity || resultCity.size() == 0) {
                        mEmptyView.setVisibility(View.VISIBLE);
                    } else {
                        mEmptyView.setVisibility(View.GONE);
                        mResultAdapter.changeData(resultCity);
                    }

                }

            }
        });
        mIvClear.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);

        mLvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                back(mResultAdapter.getItem(i).getCityName());
            }
        });
    }

    /**
     * 点击查询结果后
     *
     * @param city
     */
    private void back(String city) {
        Intent data = new Intent();
        data.putExtra(Constance.KEY_PICKED_CITY, city);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search_clear:
                mEtSearch.setText("");
                mEmptyView.setVisibility(View.GONE);
                mIvClear.setVisibility(View.GONE);

                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
