package com.bibinet.biunion.project.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.presenter.AdviceSubmitActivityPresenter;
import com.bibinet.biunion.mvp.view.AdviceSubmitActivityView;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.utils.PhoneNumberUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-26.
 */

public class AdviceSubmitActivity extends BaseActivity implements AdviceSubmitActivityView {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.submitContent)
    EditText submitContent;
    @BindView(R.id.writePhoneNum)
    EditText writePhoneNum;
    @BindView(R.id.adviceSubmit)
    Button adviceSubmit;
    private AdviceSubmitActivityPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advicesubmit);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        title.setText("意见反馈");
        titleImageleft.setVisibility(View.VISIBLE);
        presenter=new AdviceSubmitActivityPresenter(this);
    }

    @OnClick({R.id.title_imageleft, R.id.adviceSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_imageleft:
                finish();
                break;
            case R.id.adviceSubmit:
                String content=submitContent.getText().toString().trim();
                String phone=writePhoneNum.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(this,"请确保您的提交的内容不为空",Toast.LENGTH_SHORT).show();
                		}else if (!PhoneNumberUtils.isMobileNumber(phone)) {
                    Toast.makeText(this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                				}else {
                    presenter.submitAdvice(content,phone);
                }
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
    public void onSubmitSucess() {
        Toast.makeText(this,"感谢您的宝贵意见",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSubmitFailed() {
        Toast.makeText(this,"意见调失败了",Toast.LENGTH_SHORT).show();
    }
}
