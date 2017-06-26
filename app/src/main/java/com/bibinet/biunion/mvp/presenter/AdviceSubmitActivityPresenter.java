package com.bibinet.biunion.mvp.presenter;

import com.bibinet.biunion.mvp.model.AdiceSubmitActivityModel;
import com.bibinet.biunion.mvp.view.AdviceSubmitActivityView;
import com.bibinet.biunion.project.builder.MyCallBack;

/**
 * Created by bibinet on 2017-6-26.
 */

public class AdviceSubmitActivityPresenter {
    private AdiceSubmitActivityModel adiceSubmitActivityModel;
    private AdviceSubmitActivityView adviceSubmitActivityView;

    public AdviceSubmitActivityPresenter(AdviceSubmitActivityView adviceSubmitActivityView) {
        this.adviceSubmitActivityView = adviceSubmitActivityView;
        this.adiceSubmitActivityModel=new AdiceSubmitActivityModel();
    }
    public void submitAdvice(String content,String phone){
        adiceSubmitActivityModel.submitAdvice(content,phone,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                adviceSubmitActivityView.onSubmitSucess();
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                adviceSubmitActivityView.onSubmitFailed();
            }
        });
    }
}
