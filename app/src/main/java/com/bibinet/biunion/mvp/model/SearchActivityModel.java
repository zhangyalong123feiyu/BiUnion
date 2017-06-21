package com.bibinet.biunion.mvp.model;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCacheCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by bibinet on 2017-6-19.
 */

public class SearchActivityModel {
    private String cacheName="ProjectInfoCache";
    public void searProjectInfoModel(int pageNumb,String content,MyCacheCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"appData/selectPage.json");
        requestParams.addBodyParameter("pageIndex",String.valueOf(pageNumb));
        requestParams.addBodyParameter("messageLike",content);
        requestParams.setCacheMaxAge(60*60*24*15);
        requestParams.setCacheDirName(cacheName);
        requestParams.setCacheSize(1024*1024*30);
        requestParams.setMaxRetryCount(2);
        x.http().post(requestParams,myCacheCallBack);
    }
}
