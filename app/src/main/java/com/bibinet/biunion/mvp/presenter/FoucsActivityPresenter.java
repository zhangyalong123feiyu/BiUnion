package com.bibinet.biunion.mvp.presenter;

import android.util.Log;

import com.bibinet.biunion.mvp.model.FoucsActivityModel;
import com.bibinet.biunion.mvp.view.FoucsActivityView;
import com.bibinet.biunion.project.bean.FoucsedBean;
import com.bibinet.biunion.project.builder.MyCallBack;
import com.google.gson.Gson;

/**
 * Created by bibinet on 2017-6-23.
 */

public class FoucsActivityPresenter {
    private FoucsActivityView foucsActivityView;
    private FoucsActivityModel foucsActivityModel;

    public FoucsActivityPresenter(FoucsActivityView foucsActivityView) {
        this.foucsActivityView = foucsActivityView;
        this.foucsActivityModel=new FoucsActivityModel();
    }
    public void getFoucsData(final int userId, int index){
        foucsActivityModel.getFoucsData(userId,index,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                FoucsedBean foucsInfo = gson.fromJson(s, FoucsedBean.class);
                foucsActivityView.onLoadFoucsDataSucess(foucsInfo.getItem());
                Log.i("TAG","---------foucssucess----------"+s);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                foucsActivityView.onLoadFoucsDataFailed();
                Log.i("TAG","---------foucserror----------"+throwable.getMessage());
            }
        });
    }
}
