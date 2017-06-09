package com.bibinet.biunion.project.bean.cityselectbean;

/**
 * Created by zp on 2016/12/6.
 */
public abstract class BaseIndexPinyinBean extends BaseIndexBean {

    private String cityPinyin;

    public String getCityPinyin() {
        return cityPinyin;
    }

    public BaseIndexPinyinBean setCityPinyin(String cityPinyin) {
        this.cityPinyin = cityPinyin;
        return this;
    }

    /**
     * 是否需要转化成拼音
     * @return
     */
    public boolean isNeedToPinyin() {
        return true;
    }
    //需要转化成拼音的目标字段
    public abstract String getTarget();
}
