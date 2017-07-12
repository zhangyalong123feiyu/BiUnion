package com.bibinet.biunion.mvp.presenter;

import android.util.Log;
import android.widget.Toast;

import com.bibinet.biunion.mvp.model.PrivatePersonDesignModel;
import com.bibinet.biunion.mvp.view.PrivatePersonDesinView;
import com.bibinet.biunion.project.builder.MyCallBack;

/**
 * Created by bibinet on 2017-6-27.
 */

public class PrivatePersonDesinPresenter {
    private PrivatePersonDesignModel privatePersonDesignModel;
    private PrivatePersonDesinView privatePersonDesinView;

    public PrivatePersonDesinPresenter(PrivatePersonDesinView privatePersonDesinView) {
        this.privatePersonDesinView = privatePersonDesinView;
        this.privatePersonDesignModel=new PrivatePersonDesignModel();
    }
    public void onPostPrivatePersonData(int customerId,String infoType,String projectType,int regionCode){
        privatePersonDesinView.showProgress();
        privatePersonDesignModel.psotDesinInfo(customerId,infoType,projectType,regionCode,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                privatePersonDesinView.hideProgress();
                privatePersonDesinView.onDesinSucess();
                Log.i("TAG","私人订制成功了");
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                privatePersonDesinView.hideProgress();
                privatePersonDesinView.onDesinSucess();
                Log.i("TAG","私人订失败了");
                Log.i("TAG",throwable.getMessage()+"shirendinzhi------------------");

            }
        });
    }
}
