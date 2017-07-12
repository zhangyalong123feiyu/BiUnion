package com.bibinet.biunion.mvp.view;

import com.bibinet.biunion.project.bean.ProjectInfoBean;

import java.util.List;

/**
 * Created by bibinet on 2017-6-14.
 */

public interface MoreProjectView extends BaseView{
    void onLoadSucess(List<ProjectInfoBean.ItemsBean> projectInfos,boolean isLoadMore);
    void onLoadFailed(String msg);
}
