package com.bibinet.biunion.mvp.model;

import com.bibinet.biunion.project.application.Constants;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by bibinet on 2017-6-23.
 */

public class TenderHelpModel {
    public void upLoadData(){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"iip/user/login.json");
//        requestParams.addBodyParameter("account",account);
//        requestParams.addBodyParameter("password",password);
//        x.http().post(requestParams,myCacheCallBack);
    }
}
