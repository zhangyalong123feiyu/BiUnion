package com.bibinet.biunion.mvp.presenter;

import android.util.Log;

import com.bibinet.biunion.mvp.model.HelpTenderHistoryActivityModel;
import com.bibinet.biunion.mvp.view.HelpTenderHistoryActivityView;
import com.bibinet.biunion.project.bean.HelpTenderHistoryReusltBean;
import com.bibinet.biunion.project.builder.MyCallBack;
import com.google.gson.Gson;

/**
 * Created by bibinet on 2017-6-29.
 */

public class HelpTenderHistoryPresenter{
    private HelpTenderHistoryActivityModel helpTenderHistoryActivityModel;
    private HelpTenderHistoryActivityView helpTenderHistoryActivityView;

    public HelpTenderHistoryPresenter(HelpTenderHistoryActivityView helpTenderHistoryActivityView) {
        this.helpTenderHistoryActivityView = helpTenderHistoryActivityView;
        this.helpTenderHistoryActivityModel=new HelpTenderHistoryActivityModel();
    }
    public void getHelpHistoryData(String userId, int pageNumb, final boolean isLoadMore){
        helpTenderHistoryActivityModel.helpTenderHistory(userId,pageNumb,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                HelpTenderHistoryReusltBean helpHistoryInfo = gson.fromJson(s, HelpTenderHistoryReusltBean.class);
                helpTenderHistoryActivityView.onLoadHistorySucess(helpHistoryInfo.getItem(),isLoadMore);
                Log.i("TAG","投标历史记录成功++++++++++"+s);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                helpTenderHistoryActivityView.onLoadHistoryFailed(throwable.getMessage());
                Log.i("TAG","投标历史记录成功__________"+throwable.getMessage());
            }
        });
    }
}
