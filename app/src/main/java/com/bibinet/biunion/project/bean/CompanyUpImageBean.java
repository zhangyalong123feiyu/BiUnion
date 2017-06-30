package com.bibinet.biunion.project.bean;

import java.io.Serializable;

/**
 * Created by bibinet on 2017-6-30.
 */

public class CompanyUpImageBean implements Serializable {

    /**
     * originalFileInfoId : 100052
     * thumbnailFileInfoId : 100053
     * resCode : 0000
     * resMessage : 操作成功
     */

    private int originalFileInfoId;
    private int thumbnailFileInfoId;
    private String resCode;
    private String resMessage;

    public int getOriginalFileInfoId() {
        return originalFileInfoId;
    }

    public void setOriginalFileInfoId(int originalFileInfoId) {
        this.originalFileInfoId = originalFileInfoId;
    }

    public int getThumbnailFileInfoId() {
        return thumbnailFileInfoId;
    }

    public void setThumbnailFileInfoId(int thumbnailFileInfoId) {
        this.thumbnailFileInfoId = thumbnailFileInfoId;
    }

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
