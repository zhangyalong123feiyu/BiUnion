package com.bibinet.biunion.project.bean;

import java.io.Serializable;

/**
 * Created by bibinet on 2017-7-3.
 */

public class UpLoadDataResultBean implements Serializable {

    /**
     * resCode : UPenterpriseNULLuscCode
     * resMessage : 网络繁忙,请稍后重试
     * url : http://202.99.212.204:8080/iip/user/enterprise.json
     */

    private String resCode;
    private String resMessage;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

}
