package com.bibinet.biunion.mvp.presenter;

import android.util.Log;

import com.bibinet.biunion.mvp.model.MoreProjectModel;
import com.bibinet.biunion.mvp.view.MoreProjectView;
import com.bibinet.biunion.project.bean.ProjectInfoBean;
import com.bibinet.biunion.project.builder.MyCacheCallBack;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

/**
 * Created by bibinet on 2017-6-14.
 */

public class MoreProjectPresenter{
    private MoreProjectModel moreProjectModel;
    private MoreProjectView moreProjectView;

    public MoreProjectPresenter(MoreProjectView fragmentHomeView) {
        this.moreProjectView = fragmentHomeView;
        this.moreProjectModel=new MoreProjectModel();
    }
    public void LoadHomeDataMoreProjcetInfo(int pageNum,int type,int dateRange,String trad,int provinceId){
        moreProjectView.showProgress();
        moreProjectModel.LoadHomeDataMoreProjectInfo(pageNum,type, dateRange, trad,provinceId,new MyCacheCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(s, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                moreProjectView.onLoadSucess(projectinfo_list);
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                moreProjectView.onLoadFailed(throwable.getMessage());
            }
            @Override
            public boolean onCache(String s) {
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(s, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                moreProjectView.onLoadSucess(projectinfo_list);
                return super.onCache(s);
            }
        });
    }
    public void LoadHomeDataMoreTenderInfo(int pageNum,int type,int dateRange,String trad,int provinceId){
        moreProjectView.showProgress();
        moreProjectModel.LoadHomeDataMoreTenderInfo(pageNum,type, dateRange, trad,provinceId,new MyCacheCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(s, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                moreProjectView.onLoadSucess(projectinfo_list);
                moreProjectView.hideProgress();
                Log.i("TAG","fragementhomedata--------"+projectinfo_list.size());
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                moreProjectView.onLoadFailed(throwable.getMessage());
                Log.i("TAG","error--------"+throwable.getMessage());
                moreProjectView.hideProgress();
            }

            @Override
            public boolean onCache(String s) {
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(s, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                moreProjectView.onLoadSucess(projectinfo_list);
                moreProjectView.hideProgress();
                return super.onCache(s);

            }
        });
    }
    public void LoadHomeDataMoreBuyInfo(int pageNum,int type,int dateRange,String trad,int provinceId){
        moreProjectView.showProgress();
        moreProjectModel.LoadHomeDataMoreBuyInfo(pageNum,type, dateRange, trad,provinceId,new MyCacheCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(s, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                moreProjectView.onLoadSucess(projectinfo_list);
                Log.i("TAG","fragementhomedata--------"+projectinfo_list.size());
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                moreProjectView.hideProgress();
                moreProjectView.onLoadFailed(throwable.getMessage());
                Log.i("TAG","error--------"+throwable.getMessage());
            }

            @Override
            public boolean onCache(String s) {
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(s, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                moreProjectView.onLoadSucess(projectinfo_list);
                moreProjectView.hideProgress();
                return super.onCache(s);
            }
        });
    }
    public void LoadHomeDataMorePProjectInfo(int pageNum,int type,int dateRange,String trad,int provinceId){
        moreProjectView.showProgress();
        moreProjectModel.LoadHomeDataMorePpProjectInfo(pageNum,type, dateRange, trad,provinceId,new MyCacheCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(s, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                moreProjectView.onLoadSucess(projectinfo_list);
                moreProjectView.hideProgress();
                Log.i("TAG","fragementhomedata--------"+projectinfo_list.size());
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                moreProjectView.onLoadFailed(throwable.getMessage());
                moreProjectView.hideProgress();
                Log.i("TAG","error--------"+throwable.getMessage());
            }

            @Override
            public boolean onCache(String s) {
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(s, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                moreProjectView.onLoadSucess(projectinfo_list);
                moreProjectView.hideProgress();
                return super.onCache(s);
            }
        });
    }
    public void LoadHomeDataMoreApplayProjectInfo(int pageNum,int type,int dateRange,String trad,int provinceId){
        moreProjectView.showProgress();
        moreProjectModel.LoadHomeDataMoreApplayProjectInfo(pageNum,type, dateRange, trad,provinceId,new MyCacheCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(s, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                moreProjectView.onLoadSucess(projectinfo_list);
                Log.i("TAG","fragementhomedata--------"+projectinfo_list.size());
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                moreProjectView.onLoadFailed(throwable.getMessage());
                moreProjectView.hideProgress();
                Log.i("TAG","error--------"+throwable.getMessage());
            }

            @Override
            public boolean onCache(String s) {
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(s, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                moreProjectView.onLoadSucess(projectinfo_list);
                moreProjectView.hideProgress();
                return super.onCache(s);
            }
        });
    }
}
