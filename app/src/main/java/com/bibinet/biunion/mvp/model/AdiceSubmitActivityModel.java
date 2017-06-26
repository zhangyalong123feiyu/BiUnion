package com.bibinet.biunion.mvp.model;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by bibinet on 2017-6-26.
 */

public class AdiceSubmitActivityModel {
    public void submitAdvice(String content,String phone,MyCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"pis/appFeedBack/apply.json");
        requestParams.addBodyParameter("content",content);
        requestParams.addBodyParameter("cellPhone",phone);
        x.http().post(requestParams,myCacheCallBack);
    }
}
