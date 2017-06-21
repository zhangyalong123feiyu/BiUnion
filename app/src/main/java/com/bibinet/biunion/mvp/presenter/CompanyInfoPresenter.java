package com.bibinet.biunion.mvp.presenter;

import com.bibinet.biunion.mvp.model.CompanyInfoModel;
import com.bibinet.biunion.mvp.view.CompanyInfoView;

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
    public void upLoadData(){
        companyInfoModel.upLoadData();
    }
}
