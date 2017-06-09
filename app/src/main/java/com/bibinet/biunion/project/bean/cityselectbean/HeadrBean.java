package com.bibinet.biunion.project.bean.cityselectbean;

import java.util.List;

/**
 * 介绍：美团城市列表 HeaderView Bean
 * 作者：zp
 * 时间： 2016/11/28.
 */

public class HeadrBean extends BaseIndexPinyinBean {
    private List<String> cityList;
    //悬停ItemDecoration显示的Tag
    private String suspensionTag;

    public HeadrBean() {
    }

    public HeadrBean(List<String> cityList, String suspensionTag, String indexBarTag) {
        this.cityList = cityList;
        this.suspensionTag = suspensionTag;
        this.setIndexTag(indexBarTag);
    }

    public List<String> getCityList() {
        return cityList;
    }

    public HeadrBean setCityList(List<String> cityList) {
        this.cityList = cityList;
        return this;
    }

    public HeadrBean setSuspensionTag(String suspensionTag) {
        this.suspensionTag = suspensionTag;
        return this;
    }

    @Override
    public String getTarget() {
        return null;
    }

    @Override
    public boolean isNeedToPinyin() {
        return false;
    }

    @Override
    public String getSuspensionTag() {
        return suspensionTag;
    }


}
