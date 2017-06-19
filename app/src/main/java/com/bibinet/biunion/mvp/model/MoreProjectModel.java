package com.bibinet.biunion.mvp.model;

import android.os.Environment;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCacheCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by bibinet on 2017-6-14.
 */

public class MoreProjectModel {
    private String cacheName="ProjectInfoCache";
    private File cacheFile=new File(Environment.getExternalStorageDirectory(),"cachefile");
    public void LoadHomeDataMoreProjectInfo(int pageNum,int _type,int dateRange,String trad,int provinceId,MyCacheCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"/generalProjects/selectPage.json?");
        requestParams.addBodyParameter("pageNum",String.valueOf(pageNum));
        requestParams.addBodyParameter("_type",String.valueOf(_type));
        requestParams.addBodyParameter("dateRange",String.valueOf(dateRange));
        requestParams.addBodyParameter("tradeFirst",trad);
        requestParams.addBodyParameter("provinceId",String.valueOf(provinceId));
        requestParams.setCacheMaxAge(60*60*24*15);
        requestParams.setCacheDirName(cacheName);
        requestParams.setCacheSize(1024*1024*30);
        requestParams.setMaxRetryCount(2);
        x.http().post(requestParams,myCacheCallBack);
    }

    public void LoadHomeDataMoreTenderInfo(int pageNum,int _type,int dateRange,String trad,int provinceId,MyCacheCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"/biddingInfos/selectPage.json?");
        requestParams.addBodyParameter("pageNum",String.valueOf(pageNum));
        requestParams.addBodyParameter("_type",String.valueOf(_type));
        requestParams.addBodyParameter("dateRange",String.valueOf(dateRange));
        requestParams.addBodyParameter("tradeFirst",trad);
        requestParams.addBodyParameter("provinceId",String.valueOf(provinceId));
        requestParams.setCacheMaxAge(60*60*24*15);
        requestParams.setCacheDirName(cacheName);
        requestParams.setCacheSize(1024*1024*30);
        requestParams.setMaxRetryCount(2);
        x.http().post(requestParams,myCacheCallBack);
    }
    public void LoadHomeDataMoreBuyInfo(int pageNum,int _type,int dateRange,String trad,int provinceId,MyCacheCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"/purchaseInfos/selectPage.json?");
        requestParams.addBodyParameter("pageNum",String.valueOf(pageNum));
        requestParams.addBodyParameter("_type",String.valueOf(_type));
        requestParams.addBodyParameter("dateRange",String.valueOf(dateRange));
        requestParams.addBodyParameter("tradeFirst",trad);
        requestParams.addBodyParameter("provinceId",String.valueOf(provinceId));
        requestParams.setCacheMaxAge(60*60*24*15);
        requestParams.setCacheDirName(cacheName);
        requestParams.setCacheSize(1024*1024*30);
        requestParams.setMaxRetryCount(2);
        x.http().post(requestParams,myCacheCallBack);
    }
    public void LoadHomeDataMorePpProjectInfo(int pageNum,int _type,int dateRange,String trad,int provinceId,MyCacheCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"/pppProjects/selectPage.json?");
        requestParams.addBodyParameter("pageNum",String.valueOf(pageNum));
        requestParams.addBodyParameter("dateRange",String.valueOf(dateRange));
        requestParams.addBodyParameter("tradeFirst",trad);
        requestParams.addBodyParameter("provinceId",String.valueOf(provinceId));
        requestParams.setCacheMaxAge(60*60*24*15);
        requestParams.setCacheDirName(cacheName);
        requestParams.setCacheSize(1024*1024*30);
        requestParams.setMaxRetryCount(2);
        x.http().post(requestParams,myCacheCallBack);
    }
    public void LoadHomeDataMoreApplayProjectInfo(int pageNum,int _type,int dateRange,String trad,int provinceId,MyCacheCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"/portalUsers/selectPage.json?");
        requestParams.addBodyParameter("pageNum",String.valueOf(pageNum));
        requestParams.addBodyParameter("dateRange",String.valueOf(dateRange));
        requestParams.addBodyParameter("tradeFirst",trad);
        requestParams.addBodyParameter("provinceId",String.valueOf(provinceId));
        requestParams.setCacheMaxAge(60*60*24*15);
        requestParams.setCacheDirName(cacheName);
        requestParams.setCacheSize(1024*1024*30);
        requestParams.setMaxRetryCount(2);
        x.http().post(requestParams,myCacheCallBack);
    }
}
