package com.bibinet.biunion.mvp.presenter;

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
    public void upLoadUserPhoto(){
        upLoadUserPhotoModel.upLoadUserPhoto(new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                upLoadUserPhotoView.onUpLoadPhotoSucess();
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                upLoadUserPhotoView.onUpLoadPhotoFailed();
            }
        });
    }
}
