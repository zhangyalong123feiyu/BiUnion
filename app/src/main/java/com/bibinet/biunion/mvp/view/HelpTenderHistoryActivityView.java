package com.bibinet.biunion.mvp.view;

import com.bibinet.biunion.project.bean.HelpTenderHistoryReusltBean;

import java.util.List;

/**
 * Created by bibinet on 2017-6-29.
 */

public interface HelpTenderHistoryActivityView {
    void onLoadHistorySucess(List<HelpTenderHistoryReusltBean.ItemBean> historyReusltInfo,boolean isLoadMore);
    void onLoadHistoryFailed(String msg);
}
