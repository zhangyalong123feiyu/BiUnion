package com.bibinet.biunion.mvp.view;

import com.bibinet.biunion.project.bean.WriteTenderBookHistoryBean;

import java.util.List;

/**
 * Created by bibinet on 2017-6-26.
 */

public interface WriteTenderHistoryActivityView extends BaseView {
    void onLoadWriteHistroySucess(List<WriteTenderBookHistoryBean.ItemBean> historyInfo);
    void onLoadWriteHistroyFailed();
}
