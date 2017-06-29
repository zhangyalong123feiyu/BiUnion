package com.bibinet.biunion.project.ui.activity;

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
import com.bibinet.biunion.mvp.presenter.AskExpertsPresenter;
import com.bibinet.biunion.mvp.view.AskExpertsActivityView;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.utils.DialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-19.
 */

public class AskExpertsActivtiy extends BaseActivity implements AskExpertsActivityView, View.OnClickListener {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.userQuestion)
    EditText userQuestion;
    @BindView(R.id.questionContent)
    EditText questionContent;
    @BindView(R.id.isCallExperts)
    LinearLayout isCallExperts;
    @BindView(R.id.firstExperts)
    LinearLayout firstExperts;
    @BindView(R.id.secondExperts)
    LinearLayout secondExperts;
    @BindView(R.id.postData)
    Button postData;
    @BindView(R.id.callTextView)
    TextView callTextView;
    private TextView selectExperts;
    private TextView noSelectExperts;
    private String expertsCode;
    private AskExpertsPresenter askExpertsPresenter;
    private DialogUtils dialogUtils;
    private int expertsType=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_askexperts);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("专家问答");
        titleImageleft.setVisibility(View.VISIBLE);
        askExpertsPresenter = new AskExpertsPresenter(this);
    }

    @OnClick({R.id.title_imageleft, R.id.isCallExperts, R.id.firstExperts, R.id.secondExperts, R.id.postData})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_imageleft:
                finish();
                break;
            case R.id.isCallExperts:
                doCallExperts();
                break;
            case R.id.firstExperts:
                if (firstExperts.isSelected()) {
                    firstExperts.setSelected(false);
                } else {
                    firstExperts.setSelected(true);
                    secondExperts.setSelected(false);
                }
                break;
            case R.id.secondExperts:
                if (secondExperts.isSelected()) {
                    secondExperts.setSelected(false);
                } else {
                    secondExperts.setSelected(true);
                    firstExperts.setSelected(false);
                }
                break;
            case R.id.postData:
                doPostData();
                break;
        }
    }

    private void doPostData() {
        if (Constants.loginresultInfo!=null) {
            String userId = Constants.loginresultInfo.getUser().getUserId();
            String compnyId=Constants.loginresultInfo.getUser().getEnterprise().getEnterpriseId();
            String quetion = userQuestion.getText().toString().trim();
            String questionC = questionContent.getText().toString().trim();
            if (firstExperts.isSelected()) {
                expertsCode = "A";
                expertsType=1;
            } else if (selectExperts.isSelected()) {
                expertsCode = "B";
                expertsType=1;
            } else {
                expertsCode = "3";
                expertsType=3;
            }
            if (TextUtils.isEmpty(quetion) && TextUtils.isEmpty(questionC)) {
                Toast.makeText(this, "请确保您要提交的内容不为空", Toast.LENGTH_SHORT).show();
            } else {
                askExpertsPresenter.psotAskExpertsData(userId, compnyId, expertsType,expertsCode,quetion,questionC);
            }
          }else
        		{
            Toast.makeText(this,"您还没有登录，请登录后在进行提问!",Toast.LENGTH_SHORT).show();
        }



    }

    private void doCallExperts() {
        dialogUtils = new DialogUtils();
        dialogUtils.diloagShow(AskExpertsActivtiy.this, R.layout.item_isselectexperts);
        View selectDialog = dialogUtils.getView();
        selectExperts = (TextView) selectDialog.findViewById(R.id.selectExperts);
        noSelectExperts = (TextView) selectDialog.findViewById(R.id.noSelectExperts);
        selectExperts.setOnClickListener(this);
        noSelectExperts.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectExperts:
                firstExperts.setVisibility(View.VISIBLE);
                secondExperts.setVisibility(View.VISIBLE);
                callTextView.setText("是");
                dialogUtils.dialogDismiss();
                break;
            case R.id.noSelectExperts:
                expertsCode = "1";
                firstExperts.setVisibility(View.GONE);
                secondExperts.setVisibility(View.GONE);
                callTextView.setText("否");
                dialogUtils.dialogDismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public void onPostQuestionDataSucess() {
        Toast.makeText(this, "您的问题已成功提交，稍后将会有人为您进行解答", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPostQuestionDataFailed() {
        Toast.makeText(this, "未知错误，您的问题未能成提交，请稍后尝试", Toast.LENGTH_SHORT).show();
    }
}
