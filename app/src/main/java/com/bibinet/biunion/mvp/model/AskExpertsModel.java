package com.bibinet.biunion.mvp.model;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by bibinet on 2017-6-28.
 */

public class AskExpertsModel {
    public void postQuestionData(String userId,String CompanyId,int type,String expertsCode,String question,String questionContent,String code,MyCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams(Constants.baseUrl+"iip/user/sendLoginSMS.json");
        requestParams.addBodyParameter("userId",userId);
        requestParams.addBodyParameter("cellPhone",questionContent);
        requestParams.addBodyParameter("cellPhone",code);
        requestParams.addBodyParameter("cellPhone",code);
        requestParams.addBodyParameter("cellPhone",code);
        x.http().post(requestParams,myCacheCallBack);
    }
}
