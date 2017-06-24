package com.bibinet.biunion.mvp.presenter;

import com.bibinet.biunion.mvp.model.AdviceProvideActivityModel;
import com.bibinet.biunion.mvp.view.AdviceProvideActivityView;

/**
 * Created by bibinet on 2017-6-24.
 */

public class AdviceProvideActivityPrensenter {
    private AdviceProvideActivityModel provideActivityModel;
    private AdviceProvideActivityView provideActivityView;

    public AdviceProvideActivityPrensenter(AdviceProvideActivityView provideActivityView) {
        this.provideActivityView = provideActivityView;
        this.provideActivityModel=new AdviceProvideActivityModel();
    }
    public void upLoadAdvice(){
      //  provideActivityModel.upLoadAdvice();
    }
}
