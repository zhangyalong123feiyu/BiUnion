package com.bibinet.biunion.project.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bibinet on 2017-6-26.
 */

public class HotWordsBean implements Serializable {

    /**
     * resCode : 0000
     * resMessage : 操作成功
     * items : ["采购","招标开机后付款时","公告","111","aa","cs"]
     * keywordQueryFilter : {}
     */

    private String resCode;
    private String resMessage;
    private List<String> items;

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

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }


}
