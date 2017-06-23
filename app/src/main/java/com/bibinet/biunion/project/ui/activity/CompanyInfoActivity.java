package com.bibinet.biunion.project.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.view.CompanyInfoView;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCallBack;
import com.bibinet.biunion.project.utils.DialogUtils;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.IOException;

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
    private static final int REQUESTCODE_PICK = 1;//图库
    private static final int PHOTO_REQUEST_CAMERA = 3;//相机
    private static final int REQUESTCODE_CUTTING = 2;
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
                        Intent intent1 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 图片的存储路径
                        startActivityForResult(intent1, REQUESTCODE_PICK);
        				break;

        			default:
        				break;
        			}
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            //从图库选择
            case REQUESTCODE_PICK:
                if (data == null || data.getData() == null) {
                    return;
                }
                Uri uri = data.getData();
                startPhotoZoom(uri);
                // 查询选择图片
                Cursor cursor = getContentResolver().query(uri,
                        new String[] { MediaStore.Images.Media.DATA }, null,
                        null, null);
                // 光标移动至开头 获取图片路径
                cursor.moveToFirst();
                String pathImage = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Images.Media.DATA));
                File file=new File(pathImage);
                RequestParams requestParams=new RequestParams("http://202.99.212.204:8080/iip/user/uploadFile.json");
                requestParams.addBodyParameter("file",file);
                x.http().post(requestParams,new MyCallBack(){
                    @Override
                    public void onSuccess(String s) {
                        super.onSuccess(s);
                        Toast.makeText(CompanyInfoActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {
                        super.onError(throwable, b);
                        Log.i("TAG",throwable.getMessage()+"999999999999999999999999");
                    }
                });
                break;
            //拍照
            case PHOTO_REQUEST_CAMERA:
                if (hasSdcard()) {
                    Uri urid = data.getData();
                    startPhotoZoom(urid);
                }else {
                    Toast.makeText(CompanyInfoActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
                }
                break;
            //裁剪图片
            case REQUESTCODE_CUTTING:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                    }
                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
    // 开始裁剪相片
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置宽高比例
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 设置裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        // 广播刷新相册
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, REQUESTCODE_CUTTING);
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
