package com.bibinet.biunion.project.bean.cityselectbean;

/**
 * Created by zp on 2016/12/7.
 */
public class CityBean extends BaseIndexPinyinBean{
    private String cityName;//城市的名字
    private String headTag;//城市英文首字母 比如 北京 BJ

    public String getHeadTag() {
        return headTag;
    }

    public void setHeadTag(String headTag) {
        this.headTag = headTag;
    }

    public CityBean() {
    }


    public CityBean(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public CityBean setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    @Override
    public String getTarget() {
        return cityName;
    }
}
