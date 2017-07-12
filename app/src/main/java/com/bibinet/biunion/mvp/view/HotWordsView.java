package com.bibinet.biunion.mvp.view;

import com.bibinet.biunion.project.bean.HotWordsBean;

import java.util.List;

/**
 * Created by bibinet on 2017-6-26.
 */

public interface HotWordsView {
    void onLoadHotWordsSucess(HotWordsBean hotWordsBean);
    void onLoadHotWordsFailed(String msg);
}
