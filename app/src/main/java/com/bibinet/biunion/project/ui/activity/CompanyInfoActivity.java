package com.bibinet.biunion.project.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.view.CompanyInfoView;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.utils.DialogUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-3.
 */

public class CompanyInfoActivity extends BaseActivity implements CompanyInfoView,View.OnClickListener{
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.inputCompanyName)
    EditText inputCompanyName;
    @BindView(R.id.inputCreditCode)
    EditText inputCreditCode;
    @BindView(R.id.inputLegalName)
    EditText inputLegalName;
    @BindView(R.id.inputLegalIdentityCode)
    EditText inputLegalIdentityCode;
    @BindView(R.id.inputIndustry)
    EditText inputIndustry;
    @BindView(R.id.inputArea)
    EditText inputArea;
    @BindView(R.id.inputDetailAddress)
    EditText inputDetailAddress;
    @BindView(R.id.inputContactPerson)
    EditText inputContactPerson;
    @BindView(R.id.inputPhoneNumber)
    EditText inputPhoneNumber;
    @BindView(R.id.businessImage)
    ImageView businessImage;
    private static final String PHOTO_BUSINESS_NAME = "businesspic.jpg";//营业执照
    private DialogUtils dialogUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companyinfo);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Constants.loginresultInfo!=null) {
            inputCompanyName.setText(Constants.loginresultInfo.getUser().getEnterprise().getName());
            inputArea.setText(Constants.loginresultInfo.getUser().getEnterprise().getAddress().getAddr());
            inputContactPerson.setText(Constants.loginresultInfo.getUser().getEnterprise().getContactName());
            inputCreditCode.setText(Constants.loginresultInfo.getUser().getEnterprise().getCode());
        		}else {
            return;
        }
    }

    private void initView() {
        title.setText("企业资料");
        titleImageleft.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.title_imageleft, R.id.businessImage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_imageleft:
                finish();
                break;
            case R.id.businessImage:
                upLoadImage();
                break;
        }
    }

    private void upLoadImage() {
         dialogUtils=new DialogUtils();
        dialogUtils.diloagShow(this,R.layout.item_selectphoto);
        View itemview=dialogUtils.getView();
        TextView camera = (TextView)itemview.findViewById(R.id.camera);
        TextView picstorage = (TextView) itemview.findViewById(R.id.picstorage);
        camera.setOnClickListener(this);
        picstorage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        	switch (v.getId()) {
        			case R.id.camera:
                        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        File businesspic = new File(Environment.getExternalStorageDirectory()+"/BiUnion", PHOTO_BUSINESS_NAME);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(businesspic));
                        startActivity(intent);
                        dialogUtils.dialogDismiss();
        				break;
        			case R.id.picstorage:

        				break;

        			default:
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
    public void onUpLoadDataSucess() {

    }

    @Override
    public void onUpLoadDataFailed() {

    }
}
