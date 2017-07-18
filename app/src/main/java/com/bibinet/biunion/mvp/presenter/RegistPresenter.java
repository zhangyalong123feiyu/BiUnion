package com.bibinet.biunion.mvp.presenter;

import android.util.Log;
import android.widget.Toast;

import com.bibinet.biunion.mvp.model.RegistModel;
import com.bibinet.biunion.mvp.view.RegistView;
import com.bibinet.biunion.project.bean.RegistResultBean;
import com.bibinet.biunion.project.bean.VerifCodeBean;
import com.bibinet.biunion.project.builder.MyCallBack;
import com.google.gson.Gson;

/**
 * Created by bibinet on 2017-6-16.
 */

public class RegistPresenter {
    private RegistModel registModel;
    private RegistView registView;

    public RegistPresenter(RegistView registView) {
        this.registView = registView;
        this.registModel=new RegistModel();
    }
    public void getVerifCode(String phone,String type){
        registModel.getVerifCode(phone,type,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                VerifCodeBean verifInfo = gson.fromJson(s, VerifCodeBean.class);
                    registView.onVerifGetSucess(verifInfo);
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                registView.onVerifGetFailed();
                Log.i("TAG","验证码失败"+throwable.getMessage());
            }
        });
    }
    public void doRegist(String companyName,String userName,String phone,String verfiCode){
        registModel.compeletRegist(companyName,userName,phone,verfiCode,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                RegistResultBean registResultInfo = gson.fromJson(s, RegistResultBean.class);
                Log.i("TAG","注册返回json--------------------"+s);
                registView.onRegistSucess(registResultInfo);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                registView.onRegistFailed(throwable.getMessage());
            }
        });

    }

}
