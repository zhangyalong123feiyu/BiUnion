package com.bibinet.biunion.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.presenter.HelpTenderHistoryPresenter;
import com.bibinet.biunion.mvp.view.HelpTenderHistoryActivityView;
import com.bibinet.biunion.project.adapter.SearchActivityAdapter;
import com.bibinet.biunion.project.adapter.SocailFooterAdapter;
import com.bibinet.biunion.project.adapter.TenderHistoryAdapter;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.bean.HelpTenderHistoryReusltBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-29.
 */

public class HelpTenderHistoryActivity extends BaseActivity implements HelpTenderHistoryActivityView{
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.tenderHistoryRecyl)
    RecyclerView tenderHistoryRecyl;
    private LinearLayoutManager linearLayoutManager;
    private int pageNumb=1;
    private int lastvisibleitem;
    private HelpTenderHistoryPresenter presenter;
    private List<HelpTenderHistoryReusltBean.ItemBean> projectList;
    private TenderHistoryAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helptenderhistory);
        ButterKnife.bind(this);
        initView();
        loadData(false);
    }

    private void initView() {
        title.setText("投标协助历史记录");
        titleImageleft.setVisibility(View.VISIBLE);
        presenter=new HelpTenderHistoryPresenter(this);

        tenderHistoryRecyl.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(this);
        tenderHistoryRecyl.setLayoutManager(linearLayoutManager);
        tenderHistoryRecyl.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
            pageNumb++;
        } else {
            pageNumb = 1;
        }
        if (Constants.loginresultInfo!=null) {
            presenter.getHelpHistoryData(Constants.loginresultInfo.getUser().getUserId(),pageNumb,isLoadMore);
        }else{
            Toast.makeText(this,"投标协助历史记录",Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick(R.id.title_imageleft)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onLoadHistorySucess(List<HelpTenderHistoryReusltBean.ItemBean> helpHistoryInfo,boolean isLoadMore) {

        if (helpHistoryInfo.size() == 0) {
            Toast.makeText(this, "没有跟多数据了", Toast.LENGTH_SHORT).show();
            adapter.changeMoreStatus(TenderHistoryAdapter.LOAD_NODATA);
        }
        if (isLoadMore) {
            if (projectList.size() == 0) {
                projectList = helpHistoryInfo;
                adapter.changeMoreStatus(TenderHistoryAdapter.PULLUP_LOAD_MORE);
            } else {
                adapter.addMoreItem(projectList);
                adapter.changeMoreStatus(TenderHistoryAdapter.PULLUP_LOAD_MORE);
            }
        } else {
            if (projectList!=null) {
                projectList.clear();
            		}
            projectList = helpHistoryInfo;
            adapter = new TenderHistoryAdapter(this, projectList);
            tenderHistoryRecyl.setAdapter(adapter);
        }
    }
    @Override
    public void onLoadHistoryFailed(String msg) {
        Log.i("TAG","投标协助历史记录++++"+msg);
    }
}
