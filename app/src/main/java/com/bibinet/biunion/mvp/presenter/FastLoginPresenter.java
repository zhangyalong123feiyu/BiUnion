package com.bibinet.biunion.mvp.presenter;

import com.bibinet.biunion.mvp.model.FastLoginModel;
import com.bibinet.biunion.mvp.view.FastLoginView;
import com.bibinet.biunion.project.bean.FastLoginResultBean;
import com.bibinet.biunion.project.bean.FastLoginVerifCodeBean;
import com.bibinet.biunion.project.bean.VerifCodeBean;
import com.bibinet.biunion.project.builder.MyCallBack;
import com.google.gson.Gson;

/**
 * Created by bibinet on 2017-6-22.
 */

public class FastLoginPresenter {
    private FastLoginModel fastLoginModel;
    private FastLoginView fastLoginView;

    public FastLoginPresenter(FastLoginView fastLoginView) {
        this.fastLoginView = fastLoginView;
        this.fastLoginModel=new FastLoginModel();
    }
    public void getVerfiCode(String phone){
        fastLoginModel.getVerfiCode(phone,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                FastLoginVerifCodeBean verifCodeInfo = gson.fromJson(s, FastLoginVerifCodeBean.class);
                if (verifCodeInfo.getResCode().equals("0000")) {
                        fastLoginView.onGetVerfCodeSucess();
                		}
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                fastLoginView.onGetVerfCodeFailed();
            }
        });
    }
    public void fastLogin(String phone,String verifCode){
        fastLoginView.showProgress();
        fastLoginModel.FastLogin(phone,verifCode,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                FastLoginResultBean fastLoginInfo = gson.fromJson(s, FastLoginResultBean.class);
                if (fastLoginInfo.getResCode().equals("0000")) {
                    fastLoginView.onLoginSucess();
                }
                fastLoginView.hideProgress();
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                fastLoginView.hideProgress();
            }
        });
    }
}
