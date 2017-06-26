package com.bibinet.biunion.mvp.model;

import android.os.Environment;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCacheCallBack;
import com.bibinet.biunion.project.builder.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by bibinet on 2017-6-1.
 */

public class FragmentHomeModel {
    private String cacheName="ProjectInfoCache";
    private File cacheFile=new File(Environment.getExternalStorageDirectory(),"cachefile");
    public void LoadHomeDataProjectInfo(int pageNum,int _type,MyCacheCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"pis/generalProjects/selectPage.json");
        requestParams.addBodyParameter("pageNum",String.valueOf(pageNum));
        requestParams.addBodyParameter("_type",String.valueOf(_type));
        requestParams.setCacheMaxAge(60*60*24*15);
        requestParams.setCacheDirName(cacheName);
        requestParams.setCacheSize(1024*1024*30);
        requestParams.setMaxRetryCount(2);
        x.http().post(requestParams,myCacheCallBack);
    }

    public void LoadHomeDataTenderInfo(int pageNum,int _type,MyCacheCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"pis/biddingInfos/selectPage.json");
        requestParams.addBodyParameter("pageNum",String.valueOf(pageNum));
        requestParams.addBodyParameter("_type",String.valueOf(_type));
        requestParams.setCacheMaxAge(60*60*24*15);
        requestParams.setCacheDirName(cacheName);
        requestParams.setCacheSize(1024*1024*30);
        requestParams.setMaxRetryCount(2);
        x.http().post(requestParams,myCacheCallBack);
    }
    public void LoadHomeDataBuyInfo(int pageNum,int _type,MyCacheCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"pis/purchaseInfos/selectPage.json");
        requestParams.addBodyParameter("pageNum",String.valueOf(pageNum));
        requestParams.addBodyParameter("_type",String.valueOf(_type));
        requestParams.setCacheMaxAge(60*60*24*15);
        requestParams.setCacheDirName(cacheName);
        requestParams.setCacheSize(1024*1024*30);
        requestParams.setMaxRetryCount(2);
        x.http().post(requestParams,myCacheCallBack);
    }
    public void LoadHomeDataPpProjectInfo(int pageNum,int _type,MyCacheCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"pis/pppProjects/selectPage.json");
        requestParams.addBodyParameter("pageNum",String.valueOf(pageNum));
        requestParams.setCacheMaxAge(60*60*24*15);
        requestParams.setCacheDirName(cacheName);
        requestParams.setCacheSize(1024*1024*30);
        requestParams.setMaxRetryCount(2);
        x.http().post(requestParams,myCacheCallBack);
    }
    public void LoadHomeDataApplayProjectInfo(int pageNum,int _type,MyCacheCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"pis/portalUsers/selectPage.json");
        requestParams.addBodyParameter("pageNum",String.valueOf(pageNum));
        requestParams.setCacheMaxAge(60*60*24*15);
        requestParams.setCacheDirName(cacheName);
        requestParams.setCacheSize(1024*1024*30);
        requestParams.setMaxRetryCount(2);
        x.http().post(requestParams,myCacheCallBack);
    }
    public void getBannerUrl(MyCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"/pis/appData/getHomeImg.json");
        x.http().get(requestParams,myCacheCallBack);
    }
}
