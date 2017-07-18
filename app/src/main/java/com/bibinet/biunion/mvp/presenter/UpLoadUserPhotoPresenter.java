package com.bibinet.biunion.mvp.presenter;

import android.util.Log;

import com.bibinet.biunion.mvp.model.UpLoadUserPhotoModel;
import com.bibinet.biunion.mvp.view.UpLoadUserPhotoView;
import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.bean.LoginResultBean;
import com.bibinet.biunion.project.builder.MyCallBack;
import com.google.gson.Gson;

/**
 * Created by bibinet on 2017-6-30.
 */

public class UpLoadUserPhotoPresenter {
    private UpLoadUserPhotoView upLoadUserPhotoView;
    private UpLoadUserPhotoModel upLoadUserPhotoModel;

    public UpLoadUserPhotoPresenter(UpLoadUserPhotoView upLoadUserPhotoView) {
        this.upLoadUserPhotoView = upLoadUserPhotoView;
        this.upLoadUserPhotoModel=new UpLoadUserPhotoModel();
    }
    public void upLoadUserPhoto(String userId,String enterpriseId,String logofile){
        upLoadUserPhotoModel.upLoadUserPhoto(userId,enterpriseId,logofile,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                upLoadUserPhotoView.onUpLoadPhotoSucess();
                Gson gson=new Gson();
                LoginResultBean loginInfo = gson.fromJson(s, LoginResultBean.class);
                Constants.loginresultInfo=loginInfo;
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                upLoadUserPhotoView.onUpLoadPhotoFailed();
                Log.i("TAG","头像上传失败-------------------------"+throwable.getMessage());
            }
        });
    }
}
