package com.bibinet.biunion.mvp.model;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by bibinet on 2017-6-22.
 */

public class WriteTenderBookModel {
    public void upLoadTenderBookData(int tenderSelection, int projectType, int tenderMode, int tenderType, String contact, String cellPhone, String email,String customerId,MyCallBack myCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"pis/appProxyTender/apply.json");
        requestParams.addBodyParameter("tenderSelection",String.valueOf(tenderSelection));
        requestParams.addBodyParameter("projectType",String.valueOf(projectType));
        requestParams.addBodyParameter("tenderMode",String.valueOf(tenderMode));
        requestParams.addBodyParameter("tenderType",String.valueOf(tenderType));
        requestParams.addBodyParameter("contact",contact);
        requestParams.addBodyParameter("cellPhone",cellPhone);
        requestParams.addBodyParameter("email",email);
        requestParams.addBodyParameter("customerId",customerId);
        x.http().post(requestParams,myCallBack);
    }
}
