package com.bibinet.biunion.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.presenter.FastLoginPresenter;
import com.bibinet.biunion.mvp.view.FastLoginView;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.utils.DialogUtils;
import com.bibinet.biunion.project.utils.PhoneNumberUtils;
import com.bibinet.biunion.project.utils.PublicPopWindowUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-7.
 */

public class FastLoginActivity extends BaseActivity implements FastLoginView{
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.inputPhoneNumber)
    EditText inputPhoneNumber;
    @BindView(R.id.getVerifCode)
    TextView getVerifCode;
    @BindView(R.id.adverService)
    LinearLayout adverService;
    @BindView(R.id.inputVerifCode)
    EditText inputVerifCode;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private FastLoginPresenter fastLoginPresenter;
    private DialogUtils dialogUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fastlogin);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        fastLoginPresenter=new FastLoginPresenter(this);
        title.setText("快捷登录");
        titleImageleft.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.title_imageleft, R.id.getVerifCode, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_imageleft:
                finish();
                break;
            case R.id.getVerifCode:
                String phone = inputPhoneNumber.getText().toString().trim();
                if (PhoneNumberUtils.isMobileNumber(phone)) {
                    fastLoginPresenter.getVerfiCode(phone);
                }else {
                    Toast.makeText(this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_login:
                String phoneNumb = inputPhoneNumber.getText().toString().trim();
                String VerifCode = inputVerifCode.getText().toString().trim();
                if (TextUtils.isEmpty(VerifCode)) {
                			Toast.makeText(this,"验证码为空",Toast.LENGTH_SHORT).show();
                		}else {
                    fastLoginPresenter.fastLogin(phoneNumb,VerifCode);
                }
                break;
        }
    }

    @Override
    public void showProgress() {
        dialogUtils =new DialogUtils();
        dialogUtils.showProgressDialog(this,"正在登录");
    }

    @Override
    public void hideProgress() {
        dialogUtils.disProgressDialog();
    }

    @Override
    public void onGetVerfCodeSucess() {
        Toast.makeText(this,"验证码获取成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetVerfCodeFailed() {

    }

    @Override
    public void onLoginSucess() {
    finish();
    }

    @Override
    public void OnLoginFailed() {
        Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
    }
}
