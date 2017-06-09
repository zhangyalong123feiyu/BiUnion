package com.bibinet.biunion.mvp.view;

import com.bibinet.biunion.project.bean.ProjectInfoBean;

import java.util.List;

/**
 * Created by bibinet on 2017-6-1.
 */

public interface FragmentHomeView extends BaseView {
   void onLoadSucess(List<ProjectInfoBean.ItemsBean> projectinfolist);
   void onLoadFaield(String msg);
}
