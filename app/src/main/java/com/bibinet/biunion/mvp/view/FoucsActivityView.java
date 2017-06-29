package com.bibinet.biunion.mvp.view;

import com.bibinet.biunion.project.bean.FoucsedBean;

import java.util.List;

/**
 * Created by bibinet on 2017-6-23.
 */

public interface FoucsActivityView extends BaseView {
    void onLoadFoucsDataSucess(List<FoucsedBean.ItemBean> foucsInfo,boolean isLoadMore);
    void onLoadFoucsDataFailed();
}
