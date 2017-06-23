package com.bibinet.biunion.mvp.model;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by bibinet on 2017-6-22.
 */

public class FastLoginModel {
    public void getVerfiCode(String phone,MyCallBack myCacheCallBack){
            RequestParams requestParams=new RequestParams(Constants.baseUrl+"iip/user/sendLoginSMS.json");
            requestParams.addBodyParameter("cellPhone",phone);
            x.http().post(requestParams,myCacheCallBack);
    }
    public void FastLogin(String phone,String verifCode,MyCallBack myCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"iip/user/loginSMS.json");
        requestParams.addBodyParameter("cellPhone",phone);
        requestParams.addBodyParameter("checkCode",verifCode);
        x.http().post(requestParams,myCallBack);
    }
}
