package com.bibinet.biunion.mvp.view;

import com.bibinet.biunion.project.bean.ExpertsAskAnswerResultBean;

import java.util.List;

/**
 * Created by bibinet on 2017-6-30.
 */

public interface ExpertsAnswerHistoryView {
    void onGetExpertsHistoryDataSucess(List<ExpertsAskAnswerResultBean.ItemsBean> reslutInfo,boolean isLoadMore);
    void onGetExpertsHistoryFailed();
}
