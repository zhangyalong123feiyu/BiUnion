package com.bibinet.biunion.mvp.presenter;

import android.util.Log;
import android.widget.TabHost;

import com.bibinet.biunion.mvp.model.LoginModel;
import com.bibinet.biunion.mvp.view.LoginView;
import com.bibinet.biunion.project.bean.LoginResultBean;
import com.bibinet.biunion.project.builder.MyCacheCallBack;
import com.bibinet.biunion.project.builder.MyCallBack;
import com.google.gson.Gson;

/**
 * Created by bibinet on 2017-6-12.
 */

public class LoginPresenter {
    private LoginModel loginModel;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        loginModel=new LoginModel();
    }
    public void getLoginInfo(String account,String password){
        loginModel.LoadUserInfo(account,password,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                LoginResultBean loginResultInfo = gson.fromJson(s, LoginResultBean.class);
                loginView.onLoadSucess(loginResultInfo.getUser());
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                loginView.onLoadFaield(throwable.getMessage());
            }
        });
    }
}
