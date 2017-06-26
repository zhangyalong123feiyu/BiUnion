package com.bibinet.biunion.project.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.presenter.WriteTenderHistoryActivityPresenter;
import com.bibinet.biunion.mvp.view.WriteTenderHistoryActivityView;
import com.bibinet.biunion.project.adapter.WriteTenderBookHistoryAdapter;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.bean.WriteTenderBookHistoryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-14.
 */

public class WriteTenderHistoryActivity extends BaseActivity implements WriteTenderHistoryActivityView{
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.tenderWriteHistroy)
    RecyclerView tenderWriteHistroy;
    private WriteTenderHistoryActivityPresenter presenter;
    private List<WriteTenderBookHistoryBean.ItemBean> writeHistoryInfo=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writetenderhistory);
        ButterKnife.bind(this);
        initView();
        loadData();
    }

    private void initView() {
        title.setText("代写标书历史记录");
        titleImageleft.setVisibility(View.VISIBLE);
        tenderWriteHistroy.setLayoutManager(new LinearLayoutManager(this));
        tenderWriteHistroy.setHasFixedSize(true);
        presenter=new WriteTenderHistoryActivityPresenter(this);
    }
    private void loadData() {
        presenter.getWriteHistoryData("18295715877");
    }

    @OnClick(R.id.title_imageleft)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onLoadWriteHistroySucess(List<WriteTenderBookHistoryBean.ItemBean> historyInfo) {
        writeHistoryInfo=historyInfo;
        WriteTenderBookHistoryAdapter adapter=new WriteTenderBookHistoryAdapter(writeHistoryInfo,this);
        tenderWriteHistroy.setAdapter(adapter);
    }

    @Override
    public void onLoadWriteHistroyFailed() {

    }
}
