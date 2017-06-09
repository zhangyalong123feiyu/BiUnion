package com.bibinet.biunion.mvp.presenter;

import com.bibinet.biunion.mvp.model.FragmentHomeModel;
import com.bibinet.biunion.mvp.view.FragmentHomeView;
import com.bibinet.biunion.project.bean.ProjectInfoBean;
import com.bibinet.biunion.project.builder.MyCacheCallBack;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by bibinet on 2017-6-1.
 */

public class FragmentHomePresenter {
    private FragmentHomeModel fragmentHomeModel;
    private FragmentHomeView fragmentHomeView;

    public FragmentHomePresenter(FragmentHomeView fragmentHomeView) {
        this.fragmentHomeView = fragmentHomeView;
        this.fragmentHomeModel=new FragmentHomeModel();
    }
    public void LoadHomeData(){
        fragmentHomeModel.LoadHomeData(new MyCacheCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(s, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                fragmentHomeView.onLoadSucess(projectinfo_list);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                fragmentHomeView.onLoadFaield(throwable.getMessage());
            }

            @Override
            public boolean onCache(String s) {
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(s, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                fragmentHomeView.onLoadSucess(projectinfo_list);
                return super.onCache(s);
            }
        });
    }
}
