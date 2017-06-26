package com.bibinet.biunion.mvp.model;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by bibinet on 2017-6-26.
 */

public class HotWordsModel {
    public void getHotWords(MyCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"pis/appKeywords/selectKeywords.json");
        x.http().post(requestParams,myCacheCallBack);
    }
}
