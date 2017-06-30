package com.bibinet.biunion.project.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.presenter.CompanyInfoPresenter;
import com.bibinet.biunion.mvp.view.CompanyInfoView;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.bean.CompanyUpImageBean;
import com.bibinet.biunion.project.builder.MyCallBack;
import com.bibinet.biunion.project.utils.DialogUtils;
import com.bumptech.glide.Glide;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-3.
 */

public class CompanyInfoActivity extends BaseActivity implements CompanyInfoView, View.OnClickListener {
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
    private static final String PHOTO_COMPANYPIC = "businesspic.jpg";//营业执照
    @BindView(R.id.saveTenderBook)
    Button saveTenderBook;
    private DialogUtils dialogUtils;
    private static final int REQUESTCODE_PICK = 1;//图库
    private static final int PHOTO_REQUEST_CAMERA = 3;//相机
    private static final int REQUESTCODE_CUTTING = 2;
    private CompanyInfoPresenter companyInfoPresenter;
    private int thumbnailFileInfoId;
    private int orignalId;
    private File businesspic;

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
        if (Constants.loginresultInfo != null) {
            inputCompanyName.setText(Constants.loginresultInfo.getUser().getEnterprise().getEnterpriseName());
            inputArea.setText(Constants.loginresultInfo.getUser().getEnterprise().getAddr());
            inputContactPerson.setText(Constants.loginresultInfo.getUser().getEnterprise().getContactName());
            inputCreditCode.setText(Constants.loginresultInfo.getUser().getEnterprise().getUSCCode());
            inputArea.setText(Constants.loginresultInfo.getUser().getEnterprise().getRegion());
            inputDetailAddress.setText(Constants.loginresultInfo.getUser().getEnterprise().getRegion());
            inputContactPerson.setText(Constants.loginresultInfo.getUser().getEnterprise().getContactCellphone());
            Glide.with(this).load(Constants.loginresultInfo.getUser().getEnterprise().getTradingCertificateURL()).error(R.mipmap.banner_nowifi).into(businessImage);
        } else {
            return;
        }
    }

    private void initView() {
        title.setText("企业资料");
        titleImageleft.setVisibility(View.VISIBLE);
        companyInfoPresenter=new CompanyInfoPresenter(this);
    }

    @OnClick({R.id.title_imageleft, R.id.businessImage,R.id.saveTenderBook})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_imageleft:
                finish();
                break;
            case R.id.businessImage:
                upLoadImage();
                break;
            case R.id.saveTenderBook:
                saveUserInfo();
                break;
        }
    }
//上传用户资料
    private void saveUserInfo() {
        String companyName = inputCompanyName.getText().toString().trim();
        String creditCode = inputCreditCode.getText().toString().trim();
        String legalName = inputLegalName.getText().toString().trim();
        String leagalidentityCode = inputLegalIdentityCode.getText().toString().trim();
        String industry = inputIndustry.getText().toString().trim();
        String area = inputArea.getText().toString().trim();
        String detailAddress = inputDetailAddress.getText().toString().trim();
        String contactName = inputContactPerson.getText().toString().trim();
        String contactPhone= inputPhoneNumber.getText().toString().trim();
       if (TextUtils.isEmpty(companyName)&&TextUtils.isEmpty(creditCode)&&TextUtils.isEmpty(legalName)&&TextUtils.isEmpty(leagalidentityCode)&&TextUtils.isEmpty(industry)
               &&TextUtils.isEmpty(area)&&TextUtils.isEmpty(detailAddress)&&TextUtils.isEmpty(contactName)&&TextUtils.isEmpty(contactPhone)) {
       			Toast.makeText(this,"请确保您要提交的内容不为空",Toast.LENGTH_SHORT).show();
       		}else {
            companyInfoPresenter.upLoadData(companyName,creditCode,legalName,leagalidentityCode,industry,area,detailAddress,contactName,contactPhone,orignalId,thumbnailFileInfoId);
        }

    }

    private void upLoadImage() {
        dialogUtils = new DialogUtils();
        dialogUtils.diloagShow(this, R.layout.item_selectphoto);
        View itemview = dialogUtils.getView();
        TextView camera = (TextView) itemview.findViewById(R.id.camera);
        TextView picstorage = (TextView) itemview.findViewById(R.id.picstorage);
        camera.setOnClickListener(this);
        picstorage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //摄像头权限
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA},111);
                    }else {
                        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        businesspic = new File(Environment.getExternalStorageDirectory() + "/BiUnion", PHOTO_COMPANYPIC);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(businesspic));
                        startActivity(intent);
                    }
                		}
                dialogUtils.dialogDismiss();
                break;
            case R.id.picstorage:
                Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 图片的存储路径
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
                        new String[]{MediaStore.Images.Media.DATA}, null,
                        null, null);
                // 光标移动至开头 获取图片路径
                cursor.moveToFirst();
                String pathImage = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                File file = new File(pathImage);
               companyInfoPresenter.upLoadPic(file);
                break;
            //拍照
            case PHOTO_REQUEST_CAMERA:
                if (hasSdcard()) {
                    Uri urid = data.getData();
                    startPhotoZoom(urid);
                    companyInfoPresenter.upLoadPic(businesspic);
                } else {
                    Toast.makeText(CompanyInfoActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
                }
                break;
            //裁剪图片
            case REQUESTCODE_CUTTING:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                        businessImage.setImageBitmap(photo);
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 111:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"定位权限开启失败",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    File businesspic = new File(Environment.getExternalStorageDirectory() + "/BiUnion", PHOTO_COMPANYPIC);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(businesspic));
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
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
        Toast.makeText(this,"上传数据成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpLoadDataFailed() {

    }

    @Override
    public void onUpCompanyPicViewSucess(CompanyUpImageBean upImageInfo) {
        	switch (Integer.parseInt(upImageInfo.getResCode())) {
        			case 0000:
                        orignalId = upImageInfo.getOriginalFileInfoId();
                        thumbnailFileInfoId = upImageInfo.getThumbnailFileInfoId();
        				break;
        			default:
        				break;
        			}
    }

    @Override
    public void onUpCompanyPicViewFailed() {

    }

}
