package com.bibinet.biunion.mvp.presenter;

import android.util.Log;

import com.bibinet.biunion.mvp.model.AskExpertsModel;
import com.bibinet.biunion.mvp.view.AskExpertsActivityView;
import com.bibinet.biunion.project.builder.MyCallBack;

/**
 * Created by bibinet on 2017-6-28.
 */

public class AskExpertsPresenter {
    private AskExpertsActivityView askExpertsPresenterView;
    private AskExpertsModel askExpertsModel;

    public AskExpertsPresenter(AskExpertsActivityView askExpertsPresenterView) {
        this.askExpertsPresenterView = askExpertsPresenterView;
        this.askExpertsModel=new AskExpertsModel();
    }
    public void psotAskExpertsData(String userId,String CompanyId,int type,String expertsCode,String question,String questionContent){
        askExpertsModel.postQuestionData(userId,CompanyId,type,expertsCode,question,questionContent,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                askExpertsPresenterView.onPostQuestionDataSucess();
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                askExpertsPresenterView.onPostQuestionDataFailed();
                Log.i("TAG",throwable.getMessage()+"----------------------------------msg");
            }
        });
    }
}
