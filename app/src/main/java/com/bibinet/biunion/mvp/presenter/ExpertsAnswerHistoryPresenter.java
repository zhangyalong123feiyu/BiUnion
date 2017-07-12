package com.bibinet.biunion.mvp.presenter;

import android.util.Log;

import com.bibinet.biunion.mvp.model.ExpertsAnswerHistoryModel;
import com.bibinet.biunion.mvp.view.ExpertsAnswerHistoryView;
import com.bibinet.biunion.project.bean.ExpertsAskAnswerResultBean;
import com.bibinet.biunion.project.builder.MyCallBack;
import com.google.gson.Gson;

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
    public void getExpertsAnswerHistoryData(String userId, int pageNum, final boolean isLoadMore){
        expertsAnswerHistoryModel.getExpertsAnswerData(userId,pageNum,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson= new Gson();
                ExpertsAskAnswerResultBean resultInfo = gson.fromJson(s, ExpertsAskAnswerResultBean.class);
                expertsAnswerHistoryView.onGetExpertsHistoryDataSucess(resultInfo.getItems(),isLoadMore);
                Log.i("TAG",s+"------------------------问答历史");
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                expertsAnswerHistoryView.onGetExpertsHistoryFailed();
            }
        });
    }
}
