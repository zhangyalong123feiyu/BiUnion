package com.bibinet.biunion.mvp.presenter;

import android.util.Log;

import com.bibinet.biunion.mvp.model.WriteTenderHistoryActivityModel;
import com.bibinet.biunion.mvp.view.WriteTenderHistoryActivityView;
import com.bibinet.biunion.project.bean.WriteTenderBookHistoryBean;
import com.bibinet.biunion.project.builder.MyCallBack;
import com.google.gson.Gson;

/**
 * Created by bibinet on 2017-6-26.
 */

public class WriteTenderHistoryActivityPresenter {
    private WriteTenderHistoryActivityModel writeTenderHistoryActivityModel;
    private WriteTenderHistoryActivityView writeTenderHistoryActivityView;

    public WriteTenderHistoryActivityPresenter(WriteTenderHistoryActivityView writeTenderHistoryActivityView) {
        this.writeTenderHistoryActivityView = writeTenderHistoryActivityView;
        this.writeTenderHistoryActivityModel=new WriteTenderHistoryActivityModel();
    }
    public void getWriteHistoryData(String cellPhone, String customerId,String pageNum , final boolean isLoadMore){
        writeTenderHistoryActivityModel.writeTenderHistory(cellPhone,customerId,pageNum ,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson=new Gson();
                WriteTenderBookHistoryBean writeTenderHistroyInfo = gson.fromJson(s, WriteTenderBookHistoryBean.class);
                writeTenderHistoryActivityView.onLoadWriteHistroySucess(writeTenderHistroyInfo.getItem(),isLoadMore);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                writeTenderHistoryActivityView.onLoadWriteHistroyFailed();
                Log.i("TAG",throwable.getMessage()+"-------------历史记录查询失败原因---------------");
            }
        });

    }
    public void delteWriteTender(int projectId){
        writeTenderHistoryActivityModel.writeTenderHistoryDelete(projectId,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                Log.i("TAG","删除失败---------"+throwable.getMessage());
            }
        });
    }
}
