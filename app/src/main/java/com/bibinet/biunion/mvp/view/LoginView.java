package com.bibinet.biunion.mvp.view;


import com.bibinet.biunion.project.bean.LoginResultBean;

import java.util.List;

/**
 * Created by bibinet on 2017-6-12.
 */

public interface LoginView extends BaseView{
    void onLoadSucess(LoginResultBean.UserBean userInfo);
    void onLoadFaield(String msg);
}
