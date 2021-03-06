package com.bibinet.biunion.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
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
import com.bibinet.biunion.project.utils.PhoneNumberUtils;
import com.bibinet.biunion.project.utils.SharedPresUtils;
import com.google.gson.Gson;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.callback.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-7.
 */

public class LoginActivity extends BaseActivity implements LoginView{
    @BindView(R.id.title)
    TextView title;
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
    private boolean ispasswordvisible;
    private String huanXName;
    private String huanXPassword;

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
        Intent intent = getIntent();
        huanXName=intent.getStringExtra("HuanxName");
        huanXPassword=intent.getStringExtra("HuanxPassword");
        Log.i("TAG","HuanxName-------------"+huanXName);
        Log.i("TAG","HuanxPassword--------------"+huanXPassword);
    }

    @OnClick({R.id.title_imageleft, R.id.btn_login, R.id.fastLogin,R.id.seePasswrod})
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
            case R.id.seePasswrod:
                hideOrShowPassword();
                break;
        }
    }
    //隐藏或者显示密码
    private void hideOrShowPassword() {
        if (ispasswordvisible) {
            ispasswordvisible = false;
            inputPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            inputPassword.setSelection(inputPassword.getText().toString().length());
        } else {
            ispasswordvisible = true;
            inputPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            inputPassword.setSelection(inputPassword.getText().toString().length());
        }
    }

    private void doLogin() {
        LoginPresenter presenter=new LoginPresenter(this);
        String phoneNumber = inputPhoneNumber.getText().toString().trim();
        String phonePassword = inputPassword.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)&&TextUtils.isEmpty(phonePassword)) {
        			Toast.makeText(this,"手机号或者密码为空",Toast.LENGTH_SHORT).show();
        		}else {
            presenter.getLoginInfo(phoneNumber,phonePassword);
            ChatClient.getInstance().login(huanXName,huanXPassword, new Callback() {
                @Override
                public void onSuccess() {
                    Toast.makeText(LoginActivity.this,"环信登录成功",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(int i, String s) {
                    Log.i("TAG","huanxdenglushibai+"+s);
                }
                @Override
                public void onProgress(int i, String s) {

                }
            });
        }
    }

    @Override
    public void showProgress() {
        dialogUtils.showProgressDialog(this,"正在登陆");
    }

    @Override
    public void hideProgress() {
        dialogUtils.disProgressDialog();
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
    dialogUtils.disProgressDialog();
    }
}
