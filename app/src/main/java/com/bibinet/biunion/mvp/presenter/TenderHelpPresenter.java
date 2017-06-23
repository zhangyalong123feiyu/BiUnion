package com.bibinet.biunion.mvp.presenter;

import com.bibinet.biunion.mvp.model.TenderHelpModel;
import com.bibinet.biunion.mvp.view.TenderHelpView;

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
    public void upLoadData(){

    }
}
