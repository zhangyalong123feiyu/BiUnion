package com.bibinet.biunion.project.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.presenter.SearchActivityPresenter;
import com.bibinet.biunion.mvp.view.SearchActivityView;
import com.bibinet.biunion.project.adapter.SearchActivityAdapter;
import com.bibinet.biunion.project.adapter.SocailFooterAdapter;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.bean.SearchResultBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-5.
 */

public class SearchActivity extends BaseActivity implements SearchActivityView {
    @BindView(R.id.doSearch)
    ImageView doSearch;
    @BindView(R.id.searchEdit)
    EditText searchEdit;
    @BindView(R.id.cancel_search)
    TextView cancelSearch;
    @BindView(R.id.histroyRecrodRecycler)
    RecyclerView histroyRecrodRecycler;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private SearchActivityPresenter presenter;
    private List<SearchResultBean.ItemsBean> projectList = new ArrayList<>();
    private SearchActivityAdapter adapter;
    private boolean isLoadMore;
    private int lastvisibleitem;
    private int pageNum;
    private String content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searcher);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        histroyRecrodRecycler.setLayoutManager(linearLayoutManager);
        presenter = new SearchActivityPresenter(this);
        histroyRecrodRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
    private void loadData(boolean isLoadMore) {
        if (isLoadMore) {
            adapter.changeMoreStatus(SocailFooterAdapter.LOADING_MORE);
            pageNum++;
            Log.i("pageNum","pagenumber________________________"+pageNum);
        } else {
            pageNum = 1;
        }
        presenter.getSearchData(pageNum,content);
    }
    @OnClick(R.id.doSearch)
    public void onViewClicked() {
        Log.i("TAG","点击");
        content = searchEdit.getText().toString().trim();
        presenter.getSearchData(pageNum, content);
    }

    @Override
    public void showProgress() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onSearchSucess(List<SearchResultBean.ItemsBean> searchResult) {
        if (searchResult.size() == 0) {
            Toast.makeText(this, "没有跟多数据了", Toast.LENGTH_SHORT).show();
            adapter.changeMoreStatus(SocailFooterAdapter.LOAD_NODATA);
        }
        projectList = searchResult;
        if (isLoadMore) {
            if (projectList.size() == 0) {
                swipeRefresh.setRefreshing(false);
                adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
                // infoListView.scrollToPosition(InfoRefreshFootAdapter.Lastposition);
                histroyRecrodRecycler.smoothScrollToPosition(adapter.getItemCount() - 1);
            } else {
                adapter.addMoreItem(projectList);
                adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
            }
        } else {
            adapter = new SearchActivityAdapter(this, projectList);
            histroyRecrodRecycler.setAdapter(adapter);
            swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void onSearchFailed(String msg) {
        Log.i("TAG", "searcherror----------" + msg);
    }
}
