package com.bibinet.biunion.project.bean.cityselectbean;


import com.bibinet.biunion.project.builder.ISuspensionInterface;

/**
 * Created by zp on 2016/12/6.
 */
public abstract class BaseIndexBean implements ISuspensionInterface {
    private String indexTag;//城市拼音首字母


    public String getIndexTag() {
        return indexTag;
    }

    public BaseIndexBean setIndexTag(String indexTag) {
        this.indexTag = indexTag;
        return this;
    }

    @Override
    public boolean isShowSuspension() {
        return true;
    }

    @Override
    public String getSuspensionTag() {
        return indexTag;
    }
}
