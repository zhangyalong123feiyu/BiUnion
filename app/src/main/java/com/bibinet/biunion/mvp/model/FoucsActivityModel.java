package com.bibinet.biunion.mvp.model;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by bibinet on 2017-6-23.
 */

public class FoucsActivityModel {
    public void getFoucsData(int userid,int index,MyCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"pis/appCollection/selectPage.json");
        requestParams.addBodyParameter("userId",String.valueOf(userid));
        requestParams.addBodyParameter("index",String.valueOf(index));
        x.http().post(requestParams,myCacheCallBack);
    }
}
