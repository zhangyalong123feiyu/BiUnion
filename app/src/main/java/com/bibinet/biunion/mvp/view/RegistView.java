package com.bibinet.biunion.mvp.view;

import com.bibinet.biunion.project.bean.RegistResultBean;
import com.bibinet.biunion.project.bean.VerifCodeBean;

/**
 * Created by bibinet on 2017-6-16.
 */

public interface RegistView extends BaseView {
    void onVerifGetSucess(VerifCodeBean verifCodeBean);
    void onVerifGetFailed();
    void onRegistSucess(RegistResultBean registResultBean);
    void onRegistFailed(String msg);
}
