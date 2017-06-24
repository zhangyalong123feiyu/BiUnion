package com.bibinet.biunion.mvp.presenter;

import com.bibinet.biunion.mvp.model.H5ActivityModel;
import com.bibinet.biunion.mvp.view.H5ActivityView;
import com.bibinet.biunion.project.builder.MyCallBack;

/**
 * Created by bibinet on 2017-6-24.
 */

public class H5ActivityPresenter {
    private H5ActivityModel h5ActivityModel;
    private H5ActivityView h5ActivityView;

    public H5ActivityPresenter(H5ActivityView h5ActivityView) {
        this.h5ActivityView = h5ActivityView;
        this.h5ActivityModel=new H5ActivityModel();
    }
    public void collctionData(int userId,String projectId,int type){
        h5ActivityModel.collctionData(userId,projectId,type,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                h5ActivityView.onCollectoinSucess();
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                h5ActivityView.onCollectionFailed(throwable.getMessage());
            }
        });
    }
}
