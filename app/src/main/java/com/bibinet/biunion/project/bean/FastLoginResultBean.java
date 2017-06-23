package com.bibinet.biunion.project.bean;

import java.io.Serializable;

/**
 * Created by bibinet on 2017-6-22.
 */

public class FastLoginResultBean implements Serializable {
    private String resCode;
    private String resMessage;

    public String getResCode() {
        return resCode;
    }

    public String getResMessage() {
        return resMessage;
    }
}
