package com.bibinet.biunion.project.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.presenter.PrivatePersonDesinPresenter;
import com.bibinet.biunion.mvp.view.PrivatePersonDesinView;
import com.bibinet.biunion.project.adapter.PrivateAeraAdapter;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.utils.ConvertUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-1.
 */

public class PrivatePersonDesignActivity extends BaseActivity implements PrivatePersonDesinView {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;

    @BindView(R.id.areaRecyclerView)
    RecyclerView areaRecyclerView;

    private PrivatePersonDesinPresenter privatePersonDesinPresenter;
    private String projectInfoType;
    private String typeInfoType;

    private String AreaTextView;
    private ConvertUtils convertUtils;

    private String[] info = {"项目信息", "招标信息", "采购信息"};
    private String[] type = {"工程", "货物", "服务"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("信息订阅");
        privatePersonDesinPresenter = new PrivatePersonDesinPresenter(this);
        convertUtils = new ConvertUtils();
        titleImageleft.setVisibility(View.VISIBLE);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        areaRecyclerView.setLayoutManager(gridLayoutManager);
        PrivateAeraAdapter aeraAdapter = new PrivateAeraAdapter(this);
        aeraAdapter.setOnAreaClickLitioner(new PrivateAeraAdapter.AreaOnclickListioner() {
            @Override
            public void onAearClickListioner(View view) {
                AreaTextView = ((TextView) view).getText().toString();
            }
        });
        areaRecyclerView.setAdapter(aeraAdapter);
    }

    @OnClick({R.id.projcetInfo, R.id.tenderInfo, R.id.buyInfo, R.id.title_imageleft, R.id.project, R.id.goods, R.id.service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.projcetInfo:
                findViewById(view.getId()).setSelected(true);
                findViewById(R.id.tenderInfo).setSelected(false);
                findViewById(R.id.buyInfo).setSelected(false);
                projectInfoType = info[0];
                break;
            case R.id.tenderInfo:
                findViewById(view.getId()).setSelected(true);
                findViewById(R.id.projcetInfo).setSelected(false);
                findViewById(R.id.buyInfo).setSelected(false);
                projectInfoType = info[1];
                break;
            case R.id.buyInfo:
                findViewById(view.getId()).setSelected(true);
                findViewById(R.id.projcetInfo).setSelected(false);
                findViewById(R.id.tenderInfo).setSelected(false);
                projectInfoType = info[2];
                break;
            case R.id.title_imageleft:
                finish();
                break;
            case R.id.project:
                findViewById(view.getId()).setSelected(true);
                findViewById(R.id.goods).setSelected(false);
                findViewById(R.id.service).setSelected(false);
                typeInfoType = type[0];
                break;
            case R.id.goods:
                findViewById(view.getId()).setSelected(true);
                findViewById(R.id.project).setSelected(false);
                findViewById(R.id.service).setSelected(false);
                typeInfoType = type[1];
                break;
            case R.id.service:
                findViewById(view.getId()).setSelected(true);
                findViewById(R.id.project).setSelected(false);
                findViewById(R.id.goods).setSelected(false);
                typeInfoType = type[2];
                break;
        }
    }

    private boolean check() {
        if (projectInfoType == null) {
            Toast.makeText(this, "未选择信息", Toast.LENGTH_SHORT).show();
            return false;
        } else if (typeInfoType == null) {
            Toast.makeText(this, "未选择类别", Toast.LENGTH_SHORT).show();
            return false;
        } else if (AreaTextView == null) {
            Toast.makeText(this, "未选择地区", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @OnClick(R.id.postDesinInfo)
    void submit() {
        if (check()) {
            privatePersonDesinPresenter.onPostPrivatePersonData(100761, projectInfoType, typeInfoType, convertUtils.areaConvert(AreaTextView));
        }
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onDesinSucess() {
        Toast.makeText(this,"订阅成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDesinFailed() {
        Toast.makeText(this,"订阅失败",Toast.LENGTH_SHORT).show();
    }
}
