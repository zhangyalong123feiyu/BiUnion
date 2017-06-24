package com.bibinet.biunion.mvp.model;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by bibinet on 2017-6-24.
 */

public class AdviceProvideActivityModel {
    public void upLoadAdvice(String phone,MyCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"iip/user/sendLoginSMS.json");
        requestParams.addBodyParameter("cellPhone",phone);
        x.http().post(requestParams,myCacheCallBack);
    }
}
