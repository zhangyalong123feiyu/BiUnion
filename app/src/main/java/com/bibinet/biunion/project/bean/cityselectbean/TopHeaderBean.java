package com.bibinet.biunion.project.bean.cityselectbean;

/**
 * 介绍：美团最顶部Header
 * 作者：zp
 * 时间： 16/11/28.
 */

public class TopHeaderBean {
    private String txt;

    public TopHeaderBean(String txt) {
        this.txt = txt;
    }

    public String getTxt() {
        return txt;
    }

    public TopHeaderBean setTxt(String txt) {
        this.txt = txt;
        return this;
    }

}
