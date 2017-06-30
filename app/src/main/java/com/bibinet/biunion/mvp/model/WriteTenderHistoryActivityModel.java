package com.bibinet.biunion.mvp.model;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by bibinet on 2017-6-26.
 */

public class WriteTenderHistoryActivityModel {
    public void writeTenderHistory(String cellPhone,String customerId,String pageNum ,MyCallBack myCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"pis/appProxyTender/selectPage.json");
        requestParams.addBodyParameter("cellPhone",cellPhone);
        requestParams.addBodyParameter("customerId",customerId);
        requestParams.addBodyParameter("pageNum",pageNum );
        x.http().post(requestParams,myCallBack);
    }
}
