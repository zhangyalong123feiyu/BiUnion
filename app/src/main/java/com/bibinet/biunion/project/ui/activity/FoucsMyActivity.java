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
import com.bibinet.biunion.mvp.presenter.FoucsActivityPresenter;
import com.bibinet.biunion.mvp.view.FoucsActivityView;
import com.bibinet.biunion.project.adapter.FoucsMyAdapter;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.bean.FoucsedBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-10.
 */

public class FoucsMyActivity extends BaseActivity implements FoucsActivityView {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.noDataImage)
    ImageView noLoginImage;
    @BindView(R.id.foucsRecyclerView)
    RecyclerView foucsRecyclerView;
    private FoucsActivityPresenter presenter;
    private LinearLayoutManager linearLayoutManager;
    private int lastvisibleitem;
    private int pageNumb;
    private List<FoucsedBean.ItemBean> projectList;
    private boolean isLoadMore;
    private FoucsMyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foucs);
        ButterKnife.bind(this);
        initView();
        loadData(false);
    }

    private void initView() {
        String userId = Constants.loginresultInfo.getUser().getUserId();
        title.setText("我的关注");
        titleImageleft.setVisibility(View.VISIBLE);
        presenter = new FoucsActivityPresenter(this);

        presenter.getFoucsData(Integer.parseInt(userId), 1);
        foucsRecyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        foucsRecyclerView.setLayoutManager(linearLayoutManager);
        foucsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (adapter != null) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastvisibleitem + 1 == adapter.getItemCount()) {
                        loadData(true);
                        isLoadMore = true;
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
    @OnClick(R.id.title_imageleft)
    public void onViewClicked() {
        finish();
    }
    private void loadData(boolean isLoadMore) {
        if (isLoadMore) {
            adapter.changeMoreStatus(FoucsMyAdapter.LOADING_MORE);
            pageNumb++;
            Log.i("pageNum", "moeragenumber________________________" + pageNumb);
        } else {
            pageNumb = 1;
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onLoadFoucsDataSucess(List<FoucsedBean.ItemBean> foucsInfo) {
        if (foucsInfo.size() == 0) {
            Toast.makeText(this, "没有跟多数据了", Toast.LENGTH_SHORT).show();
            adapter.changeMoreStatus(FoucsMyAdapter.LOAD_NODATA);
        }
        projectList = foucsInfo;
        if (isLoadMore) {
            if (foucsInfo.size() == 0) {
                adapter.changeMoreStatus(FoucsMyAdapter.PULLUP_LOAD_MORE);
            } else {
                adapter.addMoreItem(projectList);
                adapter.changeMoreStatus(FoucsMyAdapter.PULLUP_LOAD_MORE);
            }
        } else {
            adapter = new FoucsMyAdapter(this, projectList);
            foucsRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onLoadFoucsDataFailed() {

    }


}
