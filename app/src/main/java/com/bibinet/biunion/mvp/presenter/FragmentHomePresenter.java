package com.bibinet.biunion.mvp.presenter;

import android.util.Log;
import android.widget.Toast;

import com.bibinet.biunion.mvp.model.FragmentHomeModel;
import com.bibinet.biunion.mvp.view.FragmentHomeView;
import com.bibinet.biunion.project.bean.BannerBean;
import com.bibinet.biunion.project.bean.ProjectInfoBean;
import com.bibinet.biunion.project.builder.MyCacheCallBack;
import com.bibinet.biunion.project.builder.MyCallBack;
import com.google.gson.Gson;

import org.xutils.x;

import java.util.List;

/**
 * Created by bibinet on 2017-6-1.
 */
public class FragmentHomePresenter {
    private FragmentHomeModel fragmentHomeModel;
    private FragmentHomeView fragmentHomeView;
    private boolean hasError = false;
    private String result = null;
    public FragmentHomePresenter(FragmentHomeView fragmentHomeView) {
        this.fragmentHomeView = fragmentHomeView;
        this.fragmentHomeModel=new FragmentHomeModel();
    }
    public void LoadHomeDataProjcetInfo(int pageNum, int type,int provinceId, final boolean isLoadMore){
        fragmentHomeView.showProgress();
        fragmentHomeModel.LoadHomeDataProjectInfo(pageNum,type,provinceId,new MyCacheCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                if (s!=null) {
                			result=s;
                		}
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                hasError=true;
                fragmentHomeView.hideProgress();
                fragmentHomeView.onLoadFaield(throwable.getMessage());
            }
            @Override
            public boolean onCache(String s) {
                result=s;
                return false;//如果使用false则表示不使用缓存，如果使用true则表示使用缓存
            }

            @Override
            public void onFinished() {
                super.onFinished();
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(result, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                fragmentHomeView.onLoadSucess(projectinfo_list,isLoadMore);
                fragmentHomeView.hideProgress();
            }
        });
    }
    public void LoadHomeDataTenderInfo(int pageNum, int type,int provinceId, final boolean isLoadMore){
        fragmentHomeView.showProgress();
        fragmentHomeModel.LoadHomeDataTenderInfo(pageNum,type,provinceId,new MyCacheCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                if (s!=null) {
                    result=s;
                }
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                fragmentHomeView.onLoadFaield(throwable.getMessage());
                fragmentHomeView.hideProgress();
            }

            @Override
            public boolean onCache(String s) {
                result=s;
                return false;
            }

            @Override
            public void onFinished() {
                super.onFinished();
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(result, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                fragmentHomeView.onLoadSucess(projectinfo_list,isLoadMore);
                fragmentHomeView.hideProgress();
            }
        });
    }
    public void LoadHomeDataBuyInfo(int pageNum, int type,int provinceId, final boolean isLoadMore){
        fragmentHomeView.showProgress();
        fragmentHomeModel.LoadHomeDataBuyInfo(pageNum,type,provinceId,new MyCacheCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                if (s!=null) {
                    result=s;
                }
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                hasError=true;
                fragmentHomeView.onLoadFaield(throwable.getMessage());
                fragmentHomeView.hideProgress();
            }

            @Override
            public boolean onCache(String s) {
                result=s;
                return false;
            }

            @Override
            public void onFinished() {
                super.onFinished();
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(result, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                fragmentHomeView.onLoadSucess(projectinfo_list,isLoadMore);
            }
        });

    }
    public void LoadHomeDataPProjectInfo(int pageNum, int type,int provinceId, final boolean isLoadMore){
        fragmentHomeView.showProgress();
        fragmentHomeModel.LoadHomeDataPpProjectInfo(pageNum,type,provinceId,new MyCacheCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                if (s!=null) {
                    result=s;
                }
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
                result=s;
                return false;
            }

            @Override
            public void onFinished() {
                super.onFinished();
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(result, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                fragmentHomeView.onLoadSucess(projectinfo_list,isLoadMore);
            }
        });
    }
    public void LoadHomeDataApplayProjectInfo(int pageNum, int type, int provinceId,final boolean isLoadmore){
        fragmentHomeView.showProgress();
        fragmentHomeModel.LoadHomeDataApplayProjectInfo(pageNum,type,provinceId,new MyCacheCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                if (s!=null) {
                    result=s;
                }
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
                result=s;
                return false;
            }

            @Override
            public void onFinished() {
                super.onFinished();
                Gson gson=new Gson();
                ProjectInfoBean projectInfo = gson.fromJson(result, ProjectInfoBean.class);
                List<ProjectInfoBean.ItemsBean> projectinfo_list = projectInfo.getItems();
                fragmentHomeView.onLoadSucess(projectinfo_list,isLoadmore);
            }
        });
    }
    public void getBannerData(){
        fragmentHomeModel.getBannerUrl(new MyCacheCallBack(){
            @Override
            public boolean onCache(String s) {
                result=s;
                return false;
            }

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                if (s!=null) {
                    result=s;
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                hasError=true;
                fragmentHomeView.onLoadBannerFailed();
            }

            @Override
            public void onFinished() {
                super.onFinished();
                if (!hasError && result != null) {
                    Gson gson=new Gson();
                    BannerBean bannerInfo = gson.fromJson(result, BannerBean.class);
                    fragmentHomeView.onLoadBannerSucess(bannerInfo);
                }

            }
        });
    }
}
