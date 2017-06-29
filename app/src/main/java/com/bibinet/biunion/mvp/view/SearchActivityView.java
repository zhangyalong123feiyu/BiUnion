package com.bibinet.biunion.mvp.view;

import com.bibinet.biunion.project.bean.SearchResultBean;

import java.util.List;

/**
 * Created by bibinet on 2017-6-19.
 */

public interface SearchActivityView extends BaseView {
    void onSearchSucess(List<SearchResultBean.ItemsBean> searchResult,boolean isLoadMore);
    void onSearchFailed(String msg);
}
