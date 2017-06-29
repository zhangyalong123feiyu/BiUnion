package com.bibinet.biunion.mvp.model;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by bibinet on 2017-6-23.
 */

public class TenderHelpModel {
    public void upLoadData(String contact, String cellPhone, String content,String customerId, MyCallBack myCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"pis/appAssistance/apply.json");
        requestParams.addBodyParameter("contact",contact);
        requestParams.addBodyParameter("cellPhone",cellPhone);
        requestParams.addBodyParameter("content",content);
        requestParams.addBodyParameter("customerId",customerId);
        x.http().post(requestParams,myCallBack);
    }
}
