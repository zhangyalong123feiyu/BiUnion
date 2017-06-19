package com.bibinet.biunion.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.presenter.RegistPresenter;
import com.bibinet.biunion.mvp.view.RegistView;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.utils.DialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-15.
 */

public class RegistActivity extends BaseActivity implements RegistView{
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.inputCompanyName)
    EditText inputCompanyName;
    @BindView(R.id.inputUserName)
    EditText inputUserName;
    @BindView(R.id.inputPhone)
    EditText inputPhone;
    @BindView(R.id.inputVerifCode)
    EditText inputVerifCode;
    @BindView(R.id.verifCodeBtn)
    Button verifCodeBtn;
    @BindView(R.id.completeRegist)
    Button completeRegist;
    private RegistPresenter registPresenter;
    private DialogUtils dialogUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
         registPresenter=new RegistPresenter(this);
        dialogUtils=new DialogUtils();
        title.setText("注册");
    }

    @OnClick({R.id.title_imageleft, R.id.verifCodeBtn, R.id.completeRegist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_imageleft:
                finish();
                break;
            case R.id.verifCodeBtn:
                String phoneNumb = inputPhone.getText().toString().trim();
                registPresenter.getVerifCode(phoneNumb,"1");
                break;
            case R.id.completeRegist:
                phoneNumb=inputPhone.getText().toString().trim();
                String companyName = inputCompanyName.getText().toString().trim();
                String userName = inputUserName.getText().toString().trim();
                String verifCode = inputVerifCode.getText().toString().trim();
                registPresenter.doRegist(companyName,userName,phoneNumb,verifCode);
                dialogUtils.showProgressDialog(this,"正在注册");
                break;
        }
    }



    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onVerifGetSucess() {
        Toast.makeText(this,"验证码获取成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onVerifGetFailed() {
        Toast.makeText(this,"验证码获取失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegistSucess() {
        dialogUtils.disProgressDialog();
        finish();
        startActivity(new Intent(this,LoginActivity.class));
    }

    @Override
    public void onRegistFailed() {
    dialogUtils.disProgressDialog();
    }
}
