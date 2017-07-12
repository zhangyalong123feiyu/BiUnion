package com.bibinet.biunion.mvp.presenter;

import android.util.Log;

import com.bibinet.biunion.mvp.model.HotWordsModel;
import com.bibinet.biunion.mvp.view.HotWordsView;
import com.bibinet.biunion.project.bean.HotWordsBean;
import com.bibinet.biunion.project.builder.MyCallBack;
import com.google.gson.Gson;

/**
 * Created by bibinet on 2017-6-26.
 */

public class HotWordsPresenter {
    private HotWordsModel hotWordsModel;
    private HotWordsView hotWordsView;

    public HotWordsPresenter(HotWordsView hotWordsView) {
        this.hotWordsView = hotWordsView;
        this.hotWordsModel=new HotWordsModel();
    }
    public void getHotWords(){
        hotWordsModel.getHotWords(new MyCallBack(){
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Log.i("TAG","热词-------------------------"+s);
                Gson gson=new Gson();
                HotWordsBean hotWordsInfo = gson.fromJson(s, HotWordsBean.class);
                hotWordsView.onLoadHotWordsSucess(hotWordsInfo);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                hotWordsView.onLoadHotWordsFailed(throwable.getMessage());
                Log.i("TAG","热词失败-------------------------"+throwable.getMessage());
            }
        });
    }
}
