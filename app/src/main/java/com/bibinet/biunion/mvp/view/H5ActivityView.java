package com.bibinet.biunion.mvp.view;

/**
 * Created by bibinet on 2017-6-24.
 */

public interface H5ActivityView extends BaseView {
    void onCollectoinSucess();
    void onCollectionFailed(String msg);
    void onCancelFoucsSucess();
    void onCancelFoucsFailed();
}
