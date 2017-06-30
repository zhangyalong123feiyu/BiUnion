package com.bibinet.biunion.mvp.model;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.builder.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by bibinet on 2017-6-21.
 */

public class CompanyInfoModel {
    public void upLoadData(String enterpriseName,String USCCode,String businessLicenseName,String businessLicenseCardNo,String industry,String region,String addr,String contactName,String contactCellphone,int originalFileInfoId,int thumbnailFileInfoId,MyCallBack myCallBack){
            RequestParams requestParams=new RequestParams(Constants.baseUrl+"iip/user/updateEnterpriseData.json");
            requestParams.addBodyParameter("enterpriseName",enterpriseName);
            requestParams.addBodyParameter("USCCode",USCCode);
            requestParams.addBodyParameter("businessLicenseName",businessLicenseName);
            requestParams.addBodyParameter("businessLicenseCardNo",businessLicenseCardNo);
            requestParams.addBodyParameter("industry",industry);
            requestParams.addBodyParameter("region",region);
            requestParams.addBodyParameter("addr",addr);
            requestParams.addBodyParameter("contactName",contactName);
            requestParams.addBodyParameter("contactCellphone",contactCellphone);
            requestParams.addBodyParameter("originalFileInfoId",String.valueOf(originalFileInfoId));
            requestParams.addBodyParameter("thumbnailFileInfoId",String.valueOf(thumbnailFileInfoId));
            x.http().post(requestParams,myCallBack);
    }
    public void upLoadCompanyImage(File file,MyCallBack myCallBack){
            RequestParams requestParams=new RequestParams(Constants.baseUrl+"iip/file/uploadCertificateFile.json");
            requestParams.addBodyParameter("type","1");
            requestParams.addBodyParameter("file",file);
            x.http().post(requestParams,myCallBack);
    }
}
