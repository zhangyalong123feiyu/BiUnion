package com.bibinet.biunion.project.ui.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.presenter.UpLoadUserPhotoPresenter;
import com.bibinet.biunion.mvp.view.UpLoadUserPhotoView;
import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCallBack;
import com.bibinet.biunion.project.ui.activity.AboutOurActivity;
import com.bibinet.biunion.project.ui.activity.CompanyInfoActivity;
import com.bibinet.biunion.project.ui.activity.FoucsMyActivity;
import com.bibinet.biunion.project.ui.activity.LoginActivity;
import com.bibinet.biunion.project.ui.activity.PrivatePersonDesignActivity;
import com.bibinet.biunion.project.ui.activity.RegistActivity;
import com.bibinet.biunion.project.ui.activity.SettingActivity;
import com.bibinet.biunion.project.utils.DialogUtils;
import com.bibinet.biunion.project.utils.cityselectutil.Base64MapUtils;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_My extends Fragment implements UpLoadUserPhotoView{
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.companyName)
    TextView companyName;
    //    @BindView(R.id.userName)
//    TextView userName;
    @BindView(R.id.companyInfo)
    LinearLayout companyInfo;
    @BindView(R.id.privateOdering)
    LinearLayout privateOdering;
    @BindView(R.id.foucsMy)
    LinearLayout foucsMy;
    @BindView(R.id.aboutOur)
    LinearLayout aboutOur;
    @BindView(R.id.serviceTerm)
    LinearLayout serviceTerm;
    @BindView(R.id.legalStatement)
    LinearLayout legalStatement;
    @BindView(R.id.setting)
    LinearLayout setting;
    Unbinder unbinder;
    @BindView(R.id.userPhoto_login)
    ImageView userPhotoLogin;
    @BindView(R.id.logined)
    RelativeLayout logined;
    @BindView(R.id.uerPhoto_noLogin)
    ImageView uerPhotoNoLogin;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.noLogin)
    RelativeLayout noLogin;
    @BindView(R.id.rigestBtn)
    Button rigestBtn;
    private View view;
    private DialogUtils dialogUtils;
    private UpLoadUserPhotoPresenter upLoadUserPhotoPresenter;
    private static final String PHOTO_USERPHOTO = "userphoto.jpg";//用户头像
    private static final int REQUESTCODE_PICK = 1;//图库
    private static final int PHOTO_REQUEST_CAMERA = 3;//相机
    private static final int REQUESTCODE_CUTTING = 2;
    private File businesspic;
    private Uri uriPic;

    public Fragment_My() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        title.setText("个人中心");
        upLoadUserPhotoPresenter=new UpLoadUserPhotoPresenter(this);
//        if (Constants.loginresultInfo == null) {
//            logined.setVisibility(View.GONE);
//            noLogin.setVisibility(View.VISIBLE);
//        } else {
//            logined.setVisibility(View.VISIBLE);
//            noLogin.setVisibility(View.GONE);
//            companyName.setText(Constants.loginresultInfo.getUser().getName());
//            if (TextUtils.isEmpty(Constants.loginresultInfo.getUser().getImage())) {
//                userPhotoLogin.setImageResource(R.mipmap.wode_toux);
//            }else {
//                Bitmap bitmap = Base64MapUtils.stringToBitmap(Constants.loginresultInfo.getUser().getImage());
//                userPhotoLogin.setImageBitmap(bitmap);
//            }
//        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Constants.loginresultInfo == null) {
            logined.setVisibility(View.GONE);
            noLogin.setVisibility(View.VISIBLE);
        } else {
            logined.setVisibility(View.VISIBLE);
            noLogin.setVisibility(View.GONE);
                companyName.setText(Constants.loginresultInfo.getUser().getName());
                if (TextUtils.isEmpty(Constants.loginresultInfo.getUser().getImage())) {
                		userPhotoLogin.setImageResource(R.mipmap.wode_toux);
                		}else {
                    Bitmap bitmap = Base64MapUtils.stringToBitmap(Constants.loginresultInfo.getUser().getImage());
                    userPhotoLogin.setImageBitmap(bitmap);
                }
        }
    }

    @OnClick({R.id.companyInfo, R.id.privateOdering, R.id.foucsMy, R.id.aboutOur, R.id.rigestBtn,R.id.serviceTerm, R.id.legalStatement, R.id.setting, R.id.userPhoto_login, R.id.logined, R.id.loginBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.userPhoto_login:
                selectPhoto();
                break;
            case R.id.logined:
                break;
            case R.id.loginBtn:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.companyInfo:
                startActivity(new Intent(getActivity(), CompanyInfoActivity.class));
                break;
            case R.id.privateOdering:
                startActivity(new Intent(getActivity(), PrivatePersonDesignActivity.class));
                break;
            case R.id.foucsMy:
                startActivity(new Intent(getActivity(), FoucsMyActivity.class));
                break;
            case R.id.aboutOur:
                return;
            case R.id.rigestBtn:
                startActivity(new Intent(getActivity(), RegistActivity.class));
                break;
            case R.id.serviceTerm:
                break;
            case R.id.legalStatement:
                break;
            case R.id.setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }

    }
    private void selectPhoto() {
         dialogUtils=new DialogUtils();
         dialogUtils.diloagShow(getActivity(),R.layout.item_selectphoto);
        View dialogView = dialogUtils.getView();
        TextView camera = (TextView) dialogView.findViewById(R.id.camera);
        TextView picstorage = (TextView) dialogView.findViewById(R.id.picstorage);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFromCamera();
            }
        });
        picstorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFromGallay();
            }
        });
    }

    private void selectFromGallay() {
        Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 图片的存储路径
        startActivityForResult(intent1, REQUESTCODE_PICK);
    }

    private void selectFromCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        businesspic = new File(Environment.getExternalStorageDirectory() + "/BiUnion",PHOTO_USERPHOTO);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(businesspic));
        startActivityForResult(intent,PHOTO_REQUEST_CAMERA);
        dialogUtils.dialogDismiss();
    }

    public boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            //从图库选择
            case REQUESTCODE_PICK:
                if (data == null || data.getData() == null) {
                    return;
                }
                Uri uri = data.getData();
                startPhotoZoom(uri);
                // 查询选择图片
                Cursor cursor = getActivity().getContentResolver().query(uri,
                        new String[]{MediaStore.Images.Media.DATA}, null,
                        null, null);
                // 光标移动至开头 获取图片路径
                cursor.moveToFirst();
                String pathImage = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Images.Media.DATA));
                File file = new File(pathImage);
                RequestParams requestParams = new RequestParams(Constants.baseUrl + "iip/user/uploadFile.json");
                requestParams.addBodyParameter("file", file);
                x.http().post(requestParams, new MyCallBack() {
                    @Override
                    public void onSuccess(String s) {
                        super.onSuccess(s);
                        Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {
                        super.onError(throwable, b);
                        Log.i("TAG", throwable.getMessage() + "999999999999999999999999");
                    }
        });
                break;
            //拍照
            case PHOTO_REQUEST_CAMERA:
           /*     if (data == null || data.getData() == null) {
                    Log.i("TAG","data为空了-------------------");
                    return;
                }*/
                if (hasSdcard()) {
//                    Uri urid = data.getData();
                    uriPic=Uri.fromFile(businesspic);
                    startPhotoZoom(uriPic);
                } else {
                    Toast.makeText(getActivity(), "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
                }
                break;
            //裁剪图片
            case REQUESTCODE_CUTTING:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Log.i("TAG","裁剪图片-------------------");
                        Bitmap photo = extras.getParcelable("data");
                        upLoadUserPhotoPresenter.upLoadUserPhoto(Constants.loginresultInfo.getUser().getUserId(),Constants.loginresultInfo.getUser().getEnterprise().getEnterpriseId(), Base64MapUtils.bitmapToBase64(photo));
                        Log.i("TAG","photosize-------------------"+photo.toString());
                        userPhotoLogin.setImageBitmap(photo);

                    }
                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick()
    public void onViewClicked() {
    }

    @Override
    public void onUpLoadPhotoSucess() {

    }

    @Override
    public void onUpLoadPhotoFailed() {

    }
}
