package com.bibinet.biunion.mvp.presenter;

import com.bibinet.biunion.mvp.model.TenderHelpModel;
import com.bibinet.biunion.mvp.view.TenderHelpView;
import com.bibinet.biunion.project.builder.MyCallBack;

/**
 * Created by bibinet on 2017-6-23.
 */

public class TenderHelpPresenter {
    private TenderHelpModel tenderHelpModel;
    private TenderHelpView tenderHelpView;

    public TenderHelpPresenter(TenderHelpView tenderHelpView) {
        this.tenderHelpView = tenderHelpView;
        this.tenderHelpModel=new TenderHelpModel();
    }
    public void upLoadData(String contact, String cellPhone, String content,String customerId){
        tenderHelpModel.upLoadData(contact,cellPhone,content,customerId,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                tenderHelpView.onUpLoadDataSucess();
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                tenderHelpView.onUpLoadDataFailed(throwable.getMessage());
            }
        });
    }
}
