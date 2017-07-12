package com.bibinet.biunion.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.presenter.ExpertsAnswerHistoryPresenter;
import com.bibinet.biunion.mvp.presenter.WriteTenderHistoryActivityPresenter;
import com.bibinet.biunion.mvp.view.ExpertsAnswerHistoryView;
import com.bibinet.biunion.project.adapter.ExpertsHistoryAdapter;
import com.bibinet.biunion.project.adapter.SocailFooterAdapter;
import com.bibinet.biunion.project.adapter.WriteTenderHistoryAdapter;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.bean.ExpertsAskAnswerResultBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-19.
 */

public class ExpertsAnswerActivity extends BaseActivity implements ExpertsAnswerHistoryView{
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.noExpertsHistory)
    ImageView noExpertsHistory;
    @BindView(R.id.expertsHistory)
    RecyclerView expertsHistory;
    private ExpertsAnswerHistoryPresenter presenter;
    private int pageNum=1;
    private int lastvisibleitem;
    private List<ExpertsAskAnswerResultBean.ItemsBean> projectList;
    private ExpertsHistoryAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expertsanswer);
        ButterKnife.bind(this);
        initView();
        loadData(false);
    }

    private void initView() {
        title.setText("专家问答");
        titleImageleft.setVisibility(View.VISIBLE);
        titleImageright.setVisibility(View.VISIBLE);
        titleImageright.setImageResource(R.mipmap.tiwen);

        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        expertsHistory.setLayoutManager(linearLayoutManager);
        expertsHistory.setHasFixedSize(true);
        presenter=new ExpertsAnswerHistoryPresenter(this);

        expertsHistory.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (adapter!=null) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastvisibleitem + 1 == adapter.getItemCount()) {
                        loadData(true);
                    }
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastvisibleitem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }
    private void loadData(boolean isLoadMore) {
        if (isLoadMore) {
            pageNum++;
        } else {
            pageNum= 1;
        }
        if (Constants.loginresultInfo!=null) {
            presenter.getExpertsAnswerHistoryData(Constants.loginresultInfo.getUser().getUserId(),pageNum,isLoadMore);
        }else {
            Toast.makeText(this,"您还没有咨询过专家呢！",Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick({R.id.title_imageright, R.id.title_imageleft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_imageright:
                startActivity(new Intent(this,AskExpertsActivtiy.class));
                break;
            case R.id.title_imageleft:
                finish();
                break;
        }
    }

    @Override
    public void onGetExpertsHistoryDataSucess(List<ExpertsAskAnswerResultBean.ItemsBean> resltInfo,boolean isLoadMore) {
        projectList = resltInfo;
        if (isLoadMore) {
            if (resltInfo.size() == 0) {
                Toast.makeText(this, "没有更多数据", Toast.LENGTH_SHORT).show();
                adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
            } else {
                adapter.addMoreItem(projectList);
                adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
            }
        } else {
            adapter = new ExpertsHistoryAdapter(this, projectList);
            expertsHistory.setAdapter(adapter);
        }
    }

    @Override
    public void onGetExpertsHistoryFailed() {

    }
}
