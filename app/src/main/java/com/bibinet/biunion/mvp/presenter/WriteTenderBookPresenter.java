package com.bibinet.biunion.mvp.presenter;

import com.bibinet.biunion.mvp.model.WriteTenderBookModel;
import com.bibinet.biunion.mvp.view.WriteTenderBookView;

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
}
