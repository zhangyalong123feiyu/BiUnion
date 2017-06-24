package com.bibinet.biunion.mvp.model;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by bibinet on 2017-6-24.
 */

public class H5ActivityModel {
    public void collctionData(int userId,String projectCode,int type,MyCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"pis/appCollection/collect.json");
        requestParams.addBodyParameter("userId",String.valueOf(userId));
        requestParams.addBodyParameter("relatedCode",projectCode);
        requestParams.addBodyParameter("type",String.valueOf(type));
        x.http().post(requestParams,myCacheCallBack);
    }
}
