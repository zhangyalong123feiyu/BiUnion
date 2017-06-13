package com.bibinet.biunion.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.presenter.LoginPresenter;
import com.bibinet.biunion.mvp.view.LoginView;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.bean.LoginResultBean;
import com.bibinet.biunion.project.utils.DialogUtils;
import com.bibinet.biunion.project.utils.SharedPresUtils;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-7.
 */

public class LoginActivity extends BaseActivity implements LoginView{
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    TextView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.inputPhoneNumber)
    EditText inputPhoneNumber;
    @BindView(R.id.inputPassword)
    EditText inputPassword;
    @BindView(R.id.seePasswrod)
    ImageView seePasswrod;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.fastLogin)
    Button fastLogin;
    private DialogUtils dialogUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("登录");
        dialogUtils=new DialogUtils();
    }

    @OnClick({R.id.title_imageleft, R.id.btn_login, R.id.fastLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_imageleft:
                finish();
                break;
            case R.id.btn_login:
                doLogin();
                break;
            case R.id.fastLogin:
                startActivity(new Intent(LoginActivity.this, FastLoginActivity.class));
                break;
        }
    }

    private void doLogin() {
        LoginPresenter presenter=new LoginPresenter(this);
        String phoneNumber = inputPhoneNumber.getText().toString().trim();
        String phonePassword = inputPassword.getText().toString().trim();
        presenter.getLoginInfo(phoneNumber,phonePassword);
    }

    @Override
    public void showProgress() {
        dialogUtils.showLoginDialog(this);
    }

    @Override
    public void hideProgress() {
        dialogUtils.disLoginDialog();
    }

    @Override
    public void onLoadSucess(String loginString) {
        Gson gson=new Gson();
        LoginResultBean loginResultInfo = gson.fromJson(loginString, LoginResultBean.class);
        String reslutCode = loginResultInfo.getResCode();
        	switch (Integer.parseInt(reslutCode)) {
        			case 0000:
                        Constants.loginresultInfo=loginResultInfo;
                        SharedPresUtils sharedPresUtils=SharedPresUtils.getsSharedPresUtils(this);
                        sharedPresUtils.putString("loginResultData",loginString);
                        finish();
        				break;
        			default:
        				break;
        			}

    }

    @Override
    public void onLoadFaield(String msg) {
    dialogUtils.disLoginDialog();
    }
}
