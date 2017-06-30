package com.bibinet.biunion.mvp.presenter;

import android.util.Log;

import com.bibinet.biunion.mvp.model.CompanyInfoModel;
import com.bibinet.biunion.mvp.view.CompanyInfoView;
import com.bibinet.biunion.project.bean.CompanyUpImageBean;
import com.bibinet.biunion.project.builder.MyCallBack;
import com.google.gson.Gson;

import java.io.File;

/**
 * Created by bibinet on 2017-6-21.
 */

public class CompanyInfoPresenter {
    private CompanyInfoModel companyInfoModel;
    private CompanyInfoView companyInfoView;

    public CompanyInfoPresenter(CompanyInfoView companyInfoView) {
        this.companyInfoView = companyInfoView;
        this.companyInfoModel=new CompanyInfoModel();
    }
    public void upLoadData(String enterpriseName,String USCCode,String businessLicenseName,String businessLicenseCardNo,String industry,String region,String addr,String contactName,String contactCellphone,int originalFileInfoId,int thumbnailFileInfoId){
        companyInfoModel.upLoadData(enterpriseName,USCCode,businessLicenseName,businessLicenseCardNo,industry,region,addr,contactName,contactCellphone,originalFileInfoId,thumbnailFileInfoId,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Log.i("TAG","企业信息+++++++++++++++++++++++"+s);
                companyInfoView.onUpLoadDataSucess();
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                companyInfoView.onUpLoadDataFailed();
                Log.i("TAG","企业信息上传错误+++++++++++++++++++++++"+throwable.getMessage());
            }
        });
    }
    public void upLoadPic(File file){
        companyInfoModel.upLoadCompanyImage(file,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                CompanyUpImageBean upLoadResultInfo = gson.fromJson(s, CompanyUpImageBean.class);
                companyInfoView.onUpCompanyPicViewSucess(upLoadResultInfo);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
            }
        });
    }
}
