package com.bibinet.biunion.mvp.presenter;

import com.bibinet.biunion.mvp.model.SearchActivityModel;
import com.bibinet.biunion.mvp.view.SearchActivityView;
import com.bibinet.biunion.project.builder.MyCacheCallBack;

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
    public void getSearchData(String content){
        searchActivityModel.searProjectInfoModel(content,new MyCacheCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
            }

            @Override
            public boolean onCache(String s) {
                return super.onCache(s);
            }
        });
    }
}
