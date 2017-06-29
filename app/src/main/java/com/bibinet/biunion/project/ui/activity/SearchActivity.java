package com.bibinet.biunion.project.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.presenter.HotWordsPresenter;
import com.bibinet.biunion.mvp.presenter.SearchActivityPresenter;
import com.bibinet.biunion.mvp.view.HotWordsView;
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

public class SearchActivity extends BaseActivity implements SearchActivityView, HotWordsView {
    @BindView(R.id.doSearch)
    ImageView doSearch;
    @BindView(R.id.searchEdit)
    EditText searchEdit;
    @BindView(R.id.cancel_search)
    TextView cancelSearch;
    @BindView(R.id.noResult)
    TextView noResult;
    @BindView(R.id.histroyRecrodRecycler)
    RecyclerView histroyRecrodRecycler;
    @BindView(R.id.hotOne)
    TextView hotOne;
    @BindView(R.id.hotTwo)
    TextView hotTwo;
    @BindView(R.id.hotThree)
    TextView hotThree;
    @BindView(R.id.hotFour)
    TextView hotFour;
    @BindView(R.id.hotFive)
    TextView hotFive;
    @BindView(R.id.hotSix)
    TextView hotSix;
    private SearchActivityPresenter presenter;
    private List<SearchResultBean.ItemsBean> projectList = new ArrayList<>();
    private SearchActivityAdapter adapter;
    private int lastvisibleitem;
    private int pageNum;
    private String content;
    private HotWordsPresenter hotpresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searcher);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        histroyRecrodRecycler.setLayoutManager(linearLayoutManager);
        presenter = new SearchActivityPresenter(this);
        hotpresenter = new HotWordsPresenter(this);
        hotpresenter.getHotWords();//热词presenter
        histroyRecrodRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastvisibleitem + 1 == adapter.getItemCount()) {
                        loadData(true);
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
        } else {
            pageNum = 1;
        }
        presenter.getSearchData(pageNum, content,isLoadMore);//搜索presenter
    }

    @OnClick(R.id.doSearch)
    public void onViewClicked() {
        content = searchEdit.getText().toString().trim();
        loadData(false);
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {
    }

    @Override
    public void onSearchSucess(List<SearchResultBean.ItemsBean> searchResult,boolean isLoadMore) {

        if (searchResult.size() == 0) {
            Toast.makeText(this, "没有跟多数据了", Toast.LENGTH_SHORT).show();
            adapter.changeMoreStatus(SocailFooterAdapter.LOAD_NODATA);
        }

        if (isLoadMore) {
            if (projectList.size() == 0) {
                projectList = searchResult;
                adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
            } else {
                adapter.addMoreItem(projectList);
                adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
            }
        } else {
            projectList.clear();
            projectList = searchResult;
            adapter = new SearchActivityAdapter(this, projectList);
            histroyRecrodRecycler.setVisibility(View.VISIBLE);
            histroyRecrodRecycler.setAdapter(adapter);
        }
    }

    @Override
    public void onSearchFailed(String msg) {
        Log.i("TAG", "searcherror----------" + msg);
    }

    @Override//加载热词
    public void onLoadHotWordsSucess(List<String> hotWords) {
        hotOne.setText(hotWords.get(0));
        hotTwo.setText(hotWords.get(1));
        hotThree.setText(hotWords.get(2));
        hotFour.setText(hotWords.get(3));
        hotFive.setText(hotWords.get(4));
        hotSix.setText(hotWords.get(5));
    }

    @Override
    public void onLoadHotWordsFailed() {

    }

    @OnClick({R.id.hotOne, R.id.hotTwo, R.id.hotThree, R.id.hotFour, R.id.hotFive, R.id.hotSix})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hotOne:
                content=hotOne.getText().toString().trim();
                loadData(false);
                break;
            case R.id.hotTwo:
                content=hotTwo.getText().toString().trim();
                loadData(false);
                break;
            case R.id.hotThree:
                content=hotThree.getText().toString().trim();
                loadData(false);
                break;
            case R.id.hotFour:
                content=hotFour.getText().toString().trim();
                loadData(false);
                break;
            case R.id.hotFive:
                content=hotFive.getText().toString().trim();
                loadData(false);
                break;
            case R.id.hotSix:
                content=hotSix.getText().toString().trim();
                loadData(false);
                break;
        }
    }
}
