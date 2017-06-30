package com.bibinet.biunion.mvp.model;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by bibinet on 2017-6-29.
 */

public class HelpTenderHistoryActivityModel {
    public void helpTenderHistory(String customerId,int pageNumb,MyCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"pis/appAssistance/selectPage.json");
        requestParams.addBodyParameter("customerId",customerId);
        requestParams.addBodyParameter("pageNum",String.valueOf(pageNumb));
        x.http().post(requestParams,myCacheCallBack);
    }
}
