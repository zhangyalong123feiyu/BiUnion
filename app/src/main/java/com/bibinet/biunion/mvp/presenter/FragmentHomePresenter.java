package com.bibinet.biunion.mvp.presenter;

import android.util.Log;

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
    public void LoadHomeDataProjcetInfo(int pageNum,int type){
        fragmentHomeView.showProgress();
        fragmentHomeModel.LoadHomeDataProjectInfo(pageNum,type,new MyCacheCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(s, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                fragmentHomeView.onLoadSucess(projectinfo_list);
                fragmentHomeView.hideProgress();
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                fragmentHomeView.hideProgress();
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
    public void LoadHomeDataTenderInfo(int pageNum,int type){
        fragmentHomeView.showProgress();
        fragmentHomeModel.LoadHomeDataTenderInfo(pageNum,type,new MyCacheCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(s, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                fragmentHomeView.onLoadSucess(projectinfo_list);
                fragmentHomeView.hideProgress();
                Log.i("TAG","fragementhomedata--------"+projectinfo_list.size());
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                fragmentHomeView.onLoadFaield(throwable.getMessage());
                fragmentHomeView.hideProgress();
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
    public void LoadHomeDataBuyInfo(int pageNum,int type){
        fragmentHomeView.showProgress();
        fragmentHomeModel.LoadHomeDataBuyInfo(pageNum,type,new MyCacheCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(s, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                fragmentHomeView.onLoadSucess(projectinfo_list);
                fragmentHomeView.hideProgress();
                Log.i("TAG","fragementhomedata--------"+projectinfo_list.size());
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                fragmentHomeView.onLoadFaield(throwable.getMessage());
                fragmentHomeView.hideProgress();
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
    public void LoadHomeDataPProjectInfo(int pageNum,int type){
        fragmentHomeView.showProgress();
        fragmentHomeModel.LoadHomeDataPpProjectInfo(pageNum,type,new MyCacheCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(s, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                fragmentHomeView.onLoadSucess(projectinfo_list);
                fragmentHomeView.hideProgress();
                Log.i("TAG","fragementhomedata--------"+projectinfo_list.size());
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                fragmentHomeView.onLoadFaield(throwable.getMessage());
                fragmentHomeView.hideProgress();
                Log.i("TAG","errorhomedata--------"+throwable.getMessage());
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
    public void LoadHomeDataApplayProjectInfo(int pageNum,int type){
        fragmentHomeView.showProgress();
        fragmentHomeModel.LoadHomeDataApplayProjectInfo(pageNum,type,new MyCacheCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(s, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                fragmentHomeView.onLoadSucess(projectinfo_list);
                fragmentHomeView.hideProgress();
                Log.i("TAG","fragementhomedata--------"+projectinfo_list.size());
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                fragmentHomeView.onLoadFaield(throwable.getMessage());
                fragmentHomeView.hideProgress();
                Log.i("TAG","errorhomedata--------"+throwable.getMessage());
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
