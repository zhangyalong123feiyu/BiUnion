package com.bibinet.biunion.mvp.presenter;

import com.bibinet.biunion.mvp.model.ExpertsAnswerHistoryModel;
import com.bibinet.biunion.mvp.view.ExpertsAnswerHistoryView;
import com.bibinet.biunion.project.builder.MyCallBack;

/**
 * Created by bibinet on 2017-6-30.
 */

public class ExpertsAnswerHistoryPresenter {
    private ExpertsAnswerHistoryView expertsAnswerHistoryView;
    private ExpertsAnswerHistoryModel expertsAnswerHistoryModel;

    public ExpertsAnswerHistoryPresenter(ExpertsAnswerHistoryView expertsAnswerHistoryView) {
        this.expertsAnswerHistoryView = expertsAnswerHistoryView;
        this.expertsAnswerHistoryModel=new ExpertsAnswerHistoryModel();
    }
    public void getExpertsAnswerHistoryData(){
        expertsAnswerHistoryModel.getExpertsAnswerData(new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                expertsAnswerHistoryView.onGetExpertsHistoryDataSucess();
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                expertsAnswerHistoryView.onGetExpertsHistoryFailed();
            }
        });
    }
}
