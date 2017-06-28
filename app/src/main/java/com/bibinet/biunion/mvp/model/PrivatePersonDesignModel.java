package com.bibinet.biunion.mvp.model;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by bibinet on 2017-6-27.
 */

public class PrivatePersonDesignModel {
    public void psotDesinInfo(int customerId,String infoType,String projectType,int regionCode,MyCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"pis/appMessageSubscription/saveMessage.json");
        requestParams.addBodyParameter("customerId",String.valueOf(customerId));
        requestParams.addBodyParameter("infoType",infoType);
        requestParams.addBodyParameter("projectType",projectType);
        requestParams.addBodyParameter("regionCode",String.valueOf(regionCode));
        x.http().post(requestParams,myCacheCallBack);
    }
}
