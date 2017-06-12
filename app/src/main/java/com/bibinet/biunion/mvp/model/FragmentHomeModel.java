package com.bibinet.biunion.mvp.model;

import android.os.Environment;

import com.bibinet.biunion.project.builder.MyCacheCallBack;

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
        RequestParams requestParams=new RequestParams("http://192.168.1.74:8080/pis/generalProjects/selectPage.json?");
        requestParams.addBodyParameter("pageNum",String.valueOf(pageNum));
        requestParams.addBodyParameter("_type",String.valueOf(_type));
        requestParams.setCacheMaxAge(60*60*24*15);
        requestParams.setCacheDirName(cacheName);
        requestParams.setCacheSize(1024*1024*30);
        requestParams.setMaxRetryCount(2);
        x.http().post(requestParams,myCacheCallBack);
    }

    public void LoadHomeDataTenderInfo(int pageNum,int _type,MyCacheCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams("http://192.168.1.74:8080/pis/biddingInfos/selectPage.json?");
        requestParams.addBodyParameter("pageNum",String.valueOf(pageNum));
        requestParams.addBodyParameter("_type",String.valueOf(_type));
        requestParams.setCacheMaxAge(60*60*24*15);
        requestParams.setCacheDirName(cacheName);
        requestParams.setCacheSize(1024*1024*30);
        requestParams.setMaxRetryCount(2);
        x.http().post(requestParams,myCacheCallBack);
    }
    public void LoadHomeDataBuyInfo(int pageNum,int _type,MyCacheCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams("http://192.168.1.74:8080/pis/purchaseInfos/selectPage.json?");
        requestParams.addBodyParameter("pageNum",String.valueOf(pageNum));
        requestParams.addBodyParameter("_type",String.valueOf(_type));
        requestParams.setCacheMaxAge(60*60*24*15);
        requestParams.setCacheDirName(cacheName);
        requestParams.setCacheSize(1024*1024*30);
        requestParams.setMaxRetryCount(2);
        x.http().post(requestParams,myCacheCallBack);
    }
    public void LoadHomeDataPpProjectInfo(int pageNum,int _type,MyCacheCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams("http://192.168.1.74:8080/pis/pppProjects/selectPage.json?");
        requestParams.addBodyParameter("pageNum",String.valueOf(pageNum));
        requestParams.setCacheMaxAge(60*60*24*15);
        requestParams.setCacheDirName(cacheName);
        requestParams.setCacheSize(1024*1024*30);
        requestParams.setMaxRetryCount(2);
        x.http().post(requestParams,myCacheCallBack);
    }
    public void LoadHomeDataApplayProjectInfo(int pageNum,int _type,MyCacheCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams("http://192.168.1.74:8080/pis/portalUsers/selectPage.json?pageNum=2");
        requestParams.addBodyParameter("pageNum",String.valueOf(pageNum));
        requestParams.setCacheMaxAge(60*60*24*15);
        requestParams.setCacheDirName(cacheName);
        requestParams.setCacheSize(1024*1024*30);
        requestParams.setMaxRetryCount(2);
        x.http().post(requestParams,myCacheCallBack);
    }
}
