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
import com.bibinet.biunion.mvp.presenter.TenderHelpPresenter;
import com.bibinet.biunion.mvp.view.TenderHelpView;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.utils.PhoneNumberUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-14.
 */

public class TenderHelpActivity extends BaseActivity implements TenderHelpView {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.writeBookInput)
    EditText writeBookInput;
    @BindView(R.id.contactPersonInput)
    EditText contactPersonInput;
    @BindView(R.id.contactType)
    EditText contactType;
    @BindView(R.id.postTenderhelp)
    Button postTenderhelp;
    private TenderHelpPresenter tenderHelpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenderhelp);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("投标协助");
        titleImageleft.setVisibility(View.VISIBLE);
        tenderHelpPresenter=new TenderHelpPresenter(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onUpLoadDataSucess() {
        Toast.makeText(this,"提交成功了",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpLoadDataFailed(String msg) {
        Toast.makeText(this,"很抱歉，提交失败",Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.title_imageleft, R.id.postTenderhelp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_imageleft:
                finish();
                break;
            case R.id.postTenderhelp:
                String content=writeBookInput.getText().toString().trim();
                String contactPerson=contactPersonInput.getText().toString().trim();
                String contactWay=contactType.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(this,"请输入内容",Toast.LENGTH_SHORT).show();
                		}else if (TextUtils.isEmpty(contactPerson)) {
                    Toast.makeText(this,"请输入联系人",Toast.LENGTH_SHORT).show();
            }else if (!PhoneNumberUtils.isMobileNumber(contactWay)) {
                    Toast.makeText(this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
            		}else {
                    tenderHelpPresenter.upLoadData(contactPerson,contactWay,content);
                }

                break;
        }
    }
}
