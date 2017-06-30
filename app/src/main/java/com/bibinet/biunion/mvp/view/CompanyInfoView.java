package com.bibinet.biunion.mvp.view;

import com.bibinet.biunion.project.bean.CompanyUpImageBean;

/**
 * Created by bibinet on 2017-6-21.
 */

public interface CompanyInfoView extends BaseView {
    void onUpLoadDataSucess();
    void onUpLoadDataFailed();
    void onUpCompanyPicViewSucess(CompanyUpImageBean upImageInfo);
    void onUpCompanyPicViewFailed();
}
