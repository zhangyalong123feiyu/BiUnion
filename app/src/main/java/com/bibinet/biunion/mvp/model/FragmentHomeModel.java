package com.bibinet.biunion.mvp.model;

import com.bibinet.biunion.project.builder.MyCacheCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by bibinet on 2017-6-1.
 */

public class FragmentHomeModel {

    public void LoadHomeData(MyCacheCallBack myCacheCallBack){
        RequestParams requestParams=new RequestParams("");
        requestParams.addBodyParameter("","");
        x.http().post(requestParams,myCacheCallBack);
    }

}
