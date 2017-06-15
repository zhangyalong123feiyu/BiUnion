package com.bibinet.biunion.project.utils.cityselectutil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bibinet on 2017-6-15.
 */

public class ConversionMap {
    private static   Map<String,String> industryMap=new HashMap<>();
    private static   Map<String,String> areaMap=new HashMap<>();;
    private static Map<String,String> timeMap=new HashMap<>();

    public static void allMapUtil(){
    Map<String,Map<String,String>> stringMapMap=new HashMap<>();
    stringMapMap.put("1",setIndustryMap());
    stringMapMap.put("2",setTimeMap());
    stringMapMap.put("3",setAreaMap());
}
 public static Map setIndustryMap(){
    industryMap.put("A","农、林、牧、渔业");
    industryMap.put("B","采矿业");
    industryMap.put("C","制造业");
    industryMap.put("D","电力、热力、燃气及水生产和供应业");
    industryMap.put("E","建筑业");
    industryMap.put("F","批发和零售业");
    industryMap.put("G","交通运输、仓储和邮政业");
    industryMap.put("H","住宿和餐饮业");
    industryMap.put("I","信息传输、软件和信息技术服务业");
    industryMap.put("J","金融业");
    industryMap.put("K","房地产业");
    industryMap.put("L","租赁和商务服务业");
    industryMap.put("M","科学研究和技术服务业");
    industryMap.put("N","水利、环境和公共设施管理业");
    industryMap.put("O","居民服务、修理和其他服务业");
    industryMap.put("P","教育");
    industryMap.put("Q","卫生和社会工作");
    industryMap.put("R","文化、体育和娱乐业");
    industryMap.put("S","公共管理、社会保障和社会组织");
    industryMap.put("T","国际组织");
    return industryMap;
}
public static Map setAreaMap(){
    areaMap.put("110000", "北京市");
    areaMap.put("120000","天津市");
    areaMap.put("130000", "河北省");
    areaMap.put("140000", "山西省");
    areaMap.put("150000", "内蒙古自治区");
    areaMap.put("210000", "辽宁省");
    areaMap.put("220000", "吉林省");
    areaMap.put("230000", "黑龙江省");
    areaMap.put("310000", "上海市");
    areaMap.put("320000", "江苏省");
    areaMap.put("330000", "浙江省");
    areaMap.put("340000", "安徽省");
    areaMap.put("350000", "福建省");
    areaMap.put("360000", "江西省");
    areaMap.put("370000", "山东省");
    areaMap.put("410000", "河南省");
    areaMap.put("420000", "湖北省");
    areaMap.put("430000", "湖南省");
    areaMap.put("440000", "广东省");
    areaMap.put("450000", "广西壮族自治区");
    areaMap.put("500000", "重庆市");
    areaMap.put("510000", "四川省");
    areaMap.put("520000", "贵州省");
    areaMap.put("530000", "云南省");
    areaMap.put("540000", "西藏自治区");
    areaMap.put("610000", "陕西省");
    areaMap.put("620000", "甘肃省");
    areaMap.put("630000", "青海省");
    areaMap.put("640000", "宁夏回族自治区");
    areaMap.put(  "650000", "新疆维吾尔自治区");
    areaMap.put("710000", "台湾省");
    areaMap.put("810000", "香港特别行政区");
    areaMap.put("820000", "澳门特别行政区");
    return areaMap;
}
public static Map setTimeMap(){
    timeMap.put("1","一周");
    timeMap.put("2","一个月");
    timeMap.put("3","半年");
    return timeMap;
}
}
