package com.bibinet.biunion.mvp.view;

/**
 * Created by bibinet on 2017-6-22.
 */

public interface FastLoginView extends BaseView {
    void onGetVerfCodeSucess();
    void onGetVerfCodeFailed();
    void onLoginSucess();
    void OnLoginFailed();
}
