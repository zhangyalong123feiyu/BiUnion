package com.bibinet.biunion.mvp.presenter;

import android.util.Log;

import com.bibinet.biunion.mvp.model.UpLoadUserPhotoModel;
import com.bibinet.biunion.mvp.view.UpLoadUserPhotoView;
import com.bibinet.biunion.project.builder.MyCallBack;

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
                Log.i("TAG","phtoots-------------------------"+s);
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
