package com.bibinet.biunion.mvp.presenter;

import android.util.Log;

import com.bibinet.biunion.mvp.model.WriteTenderBookModel;
import com.bibinet.biunion.mvp.view.WriteTenderBookView;
import com.bibinet.biunion.project.builder.MyCallBack;

/**
 * Created by bibinet on 2017-6-22.
 */

public class WriteTenderBookPresenter {
    private WriteTenderBookView writeTenderBookView;
    private WriteTenderBookModel writeTenderBookModel;

    public WriteTenderBookPresenter(WriteTenderBookView writeTenderBookView) {
        this.writeTenderBookView = writeTenderBookView;
        this.writeTenderBookModel=new WriteTenderBookModel();
    }
    public void saveWriteTenderBook(int tenderSelection,int projectType,int tenderMode,int tenderType,String contact,String cellPhone,String email,String customerId){
        writeTenderBookModel.upLoadTenderBookData(tenderSelection,projectType,tenderMode,tenderType,contact,cellPhone,email,customerId,new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                writeTenderBookView.saveTenderBookSucess();
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                writeTenderBookView.saveTenderBookFailed();
                Log.i("TAG",throwable.getMessage()+"带写标书-----------------error");
            }
        });
    }

}
