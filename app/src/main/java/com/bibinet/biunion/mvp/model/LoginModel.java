package com.bibinet.biunion.mvp.model;

import android.os.Environment;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;


/**
 * Created by bibinet on 2017-6-12.
 */

public class LoginModel  {
    public void LoadUserInfo(String account,String password,MyCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"/user/login.json");
        requestParams.addBodyParameter("account",account);
        requestParams.addBodyParameter("password",password);
        x.http().post(requestParams,myCacheCallBack);
    }

}
