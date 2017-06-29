package com.bibinet.biunion.mvp.presenter;

import android.util.Log;

import com.bibinet.biunion.mvp.model.SearchActivityModel;
import com.bibinet.biunion.mvp.view.SearchActivityView;
import com.bibinet.biunion.project.bean.SearchResultBean;
import com.bibinet.biunion.project.builder.MyCacheCallBack;
import com.google.gson.Gson;

/**
 * Created by bibinet on 2017-6-19.
 */

public class SearchActivityPresenter {
    private SearchActivityView searchActivityView;
    private SearchActivityModel searchActivityModel;

    public SearchActivityPresenter(SearchActivityView searchActivityView) {
        this.searchActivityView = searchActivityView;
        this.searchActivityModel=new SearchActivityModel();
    }
    public void getSearchData(int pageNumb, String content, final boolean isLoadMore){
        searchActivityView.showProgress();
        searchActivityModel.searProjectInfoModel(pageNumb,content,new MyCacheCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                SearchResultBean searchResultInfo = gson.fromJson(s, SearchResultBean.class);
                searchActivityView.onSearchSucess(searchResultInfo.getItems(),isLoadMore);
                searchActivityView.hideProgress();
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                searchActivityView.onSearchFailed(throwable.getMessage());
                searchActivityView.hideProgress();
            }

            @Override
            public boolean onCache(String s) {
                Gson gson=new Gson();
                SearchResultBean searchResultInfo = gson.fromJson(s, SearchResultBean.class);
                searchActivityView.onSearchSucess(searchResultInfo.getItems(),isLoadMore);
                searchActivityView.hideProgress();
                return super.onCache(s);
            }
        });
    }
}
