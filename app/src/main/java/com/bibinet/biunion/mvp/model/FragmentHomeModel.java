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
    public void LoadHomeData(MyCacheCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams("http://192.168.1.74:8080/pis/biddingInfos/selectPage.json?");
        requestParams.addBodyParameter("pageNum","1");
        requestParams.addBodyParameter("_type","1");
        requestParams.setCacheMaxAge(60*60*24*15);
        requestParams.setCacheDirName(cacheName);
        requestParams.setCacheSize(1024*1024*30);
        requestParams.setMaxRetryCount(2);
        x.http().post(requestParams,myCacheCallBack);
    }

}
