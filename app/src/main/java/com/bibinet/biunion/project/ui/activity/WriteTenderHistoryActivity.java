package com.bibinet.biunion.project.ui.activity;

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
import com.bibinet.biunion.mvp.presenter.WriteTenderHistoryActivityPresenter;
import com.bibinet.biunion.mvp.view.WriteTenderHistoryActivityView;
import com.bibinet.biunion.project.adapter.SocailFooterAdapter;
import com.bibinet.biunion.project.adapter.TenderHistoryAdapter;
import com.bibinet.biunion.project.adapter.WriteTenderHistoryAdapter;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.application.Constants;
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
    private int pageNumb=1;
    private List<WriteTenderBookHistoryBean.ItemBean> projectList;
    private WriteTenderHistoryAdapter adapter;
    private int lastvisibleitem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writetenderhistory);
        ButterKnife.bind(this);
        initView();
        loadData(false);
    }

    private void initView() {
        title.setText("代写标书历史记录");
        titleImageleft.setVisibility(View.VISIBLE);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        tenderWriteHistroy.setLayoutManager(linearLayoutManager);
        tenderWriteHistroy.setHasFixedSize(true);
        presenter=new WriteTenderHistoryActivityPresenter(this);

        tenderWriteHistroy.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
            pageNumb= 1;
        }
        if (Constants.loginresultInfo!=null) {
            presenter.getWriteHistoryData(Constants.loginresultInfo.getUser().getEnterprise().getContactCellphone(),Constants.loginresultInfo.getUser().getUserId(),String.valueOf(pageNumb),isLoadMore);
        }else {
            Toast.makeText(this,"您还没有代写标书历史了！",Toast.LENGTH_SHORT).show();
        }
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
    public void onLoadWriteHistroySucess(List<WriteTenderBookHistoryBean.ItemBean> historyInfo,boolean isLoadMore) {
        projectList = historyInfo;
        Log.i("TAG","--------------------"+historyInfo.size());
        if (isLoadMore) {
            if (historyInfo.size() == 0) {
                Toast.makeText(this, "没有更多数据", Toast.LENGTH_SHORT).show();
                adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
            } else {
                adapter.addMoreItem(projectList);
                adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
            }
        } else {
            adapter = new WriteTenderHistoryAdapter(this, projectList);
            tenderWriteHistroy.setAdapter(adapter);
            adapter.setOnItemClickListioner(new WriteTenderHistoryAdapter.OnItemClickListioner() {
                @Override
                public void ItemClickListioner(int position, int objectId) {
                    presenter.delteWriteTender(objectId);
                    Log.i("TAG","projectListSize--------------------"+projectList.size());
                    Log.i("TAG","objectId----------position-------"+position+"objectid"+objectId);
                    projectList.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void onLoadWriteHistroyFailed() {
        Toast.makeText(this, "您查询历史记录失败！", Toast.LENGTH_SHORT).show();
    }
}
