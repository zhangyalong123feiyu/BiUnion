package com.bibinet.biunion.mvp.view;

import com.bibinet.biunion.project.bean.BannerBean;
import com.bibinet.biunion.project.bean.ProjectInfoBean;

import java.util.List;

/**
 * Created by bibinet on 2017-6-1.
 */

public interface FragmentHomeView extends BaseView {
   void onLoadSucess(List<ProjectInfoBean.ItemsBean> projectinfolist,boolean isLoadMore);
   void onLoadFaield(String msg);
   void onLoadBannerSucess(List<BannerBean.ItemBean> bannerInfo);
   void onLoadBannerFailed();
}
