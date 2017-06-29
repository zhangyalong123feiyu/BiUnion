package com.bibinet.biunion.mvp.model;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by bibinet on 2017-6-28.
 */

public class AskExpertsModel {
    public void postQuestionData(String userId,String CompanyId,int type,String expertsCode,String question,String questionContent,MyCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"iip/appQuestion/saveQuestion.json");
        requestParams.addBodyParameter("userId",userId);
        requestParams.addBodyParameter("enterpriseId",CompanyId);
        requestParams.addBodyParameter("type",String.valueOf(type));
        requestParams.addBodyParameter("expertCodeStr",expertsCode);
        requestParams.addBodyParameter("title",question);
        requestParams.addBodyParameter("content",questionContent);
        x.http().post(requestParams,myCacheCallBack);
    }
}
