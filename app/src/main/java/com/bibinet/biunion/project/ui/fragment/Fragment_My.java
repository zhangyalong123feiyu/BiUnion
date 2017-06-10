package com.bibinet.biunion.project.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.ui.activity.CompanyInfoActivity;
import com.bibinet.biunion.project.ui.activity.FoucsMyActivity;
import com.bibinet.biunion.project.ui.activity.LoginActivity;
import com.bibinet.biunion.project.ui.activity.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_My extends Fragment {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.companyName)
    TextView companyName;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.companyInfo)
    LinearLayout companyInfo;
    @BindView(R.id.privateOdering)
    LinearLayout privateOdering;
    @BindView(R.id.foucsMy)
    LinearLayout foucsMy;
    @BindView(R.id.product)
    LinearLayout product;
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
    private View view;

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
    }

    @OnClick({R.id.companyInfo, R.id.privateOdering, R.id.foucsMy, R.id.product, R.id.aboutOur, R.id.serviceTerm, R.id.legalStatement, R.id.setting,R.id.userPhoto_login, R.id.logined, R.id.loginBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.userPhoto_login:
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
                break;
            case R.id.foucsMy:
                startActivity(new Intent(getActivity(), FoucsMyActivity.class));
                break;
            case R.id.product:
                break;
            case R.id.aboutOur:
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
