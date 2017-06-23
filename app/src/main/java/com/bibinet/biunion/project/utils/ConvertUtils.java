package com.bibinet.biunion.project.utils;

/**
 * Created by bibinet on 2017-6-20.
 */

public class ConvertUtils {
    public String industryConvert(String industry){
//		if (industry.equals("行业")) {
//			return "A";
//		}else if (industry.equals("全部")) {
//					return "A";
//		}else if (industry.equals("农、林、牧、渔业")) {
//			return "A";
//		}else if (industry.equals("采矿业")) {
//			return "B";
//		}else if (industry.equals("制造业")) {
//			return "C";
//		}else if (industry.equals("电力、热力、燃气及水生产和供应业")) {
//			return "D";
//		}else if (industry.equals("建筑业")) {
//			return "E";
//		}else if (industry.equals("批发和零售业")) {
//			return "F";
//		}else if (industry.equals("交通运输、仓储和邮政业")) {
//			return "G";
//		}else if (industry.equals("住宿和餐饮业")) {
//			return "H";
//		}else if (industry.equals("信息传输、软件和信息技术服务业")) {
//			return "I";
//		}else if (industry.equals("金融业")) {
//			return "K";
//		}else if (industry.equals("租赁和商务服务业")) {
//			return "J";
//		}else if (industry.equals("科学研究和技术服务业")) {
//			return "L";
//		}else if (industry.equals("水利、环境和公共设施管理业")) {
//			return "M";
//		}else if (industry.equals("居民服务、修理和其他服务业")) {
//			return "N";
//		}else if (industry.equals("教育")) {
//			return "O";
//		}else if (industry.equals("卫生和社会工作")) {
//			return "P";
//		}else if (industry.equals("文化、体育和娱乐业")) {
//			return "Q";
//		}else if (industry.equals("公共管理、社会保障和社会组织")) {
//			return "R";
//		}else if (industry.equals("教育")) {
//			return "S";
//		}else{
//			return "T";
//		}
//    }
		String industryNew="";
        	switch (industry) {
				    case "行业":
				    	industryNew="";
					    break;
				    case "全部":
						industryNew="";
						break;
        			case "农、林、牧、渔业":
						industryNew="A";
						break;
        			case "采矿业":
						industryNew="B";
                        break;
        			case "制造业":
						industryNew="C";
						break;
        			case "电力、热力、燃气及水生产和供应业":
						industryNew="D";
						break;
        			case "建筑业":
						industryNew="E";
						break;
        			case "批发和零售业":
						industryNew="F";
						break;
        			case "交通运输、仓储和邮政业":
						industryNew="B";
						break;
        			case "住宿和餐饮业":
						industryNew="H";
						break;
        			case "信息传输、软件和信息技术服务业":
						industryNew="I";
						break;
        			case "金融业":
						industryNew="J";
						break;
        			case "房地产业":
						industryNew="K";
						break;
        			case "租赁和商务服务业":
						industryNew="L";
						break;
        			case "科学研究和技术服务业":
						industryNew="M";
						break;
        			case "水利、环境和公共设施管理业":
						industryNew="N";
						break;
        			case "居民服务、修理和其他服务业":
						industryNew="O";
						break;
        			case "教育":
						industryNew="P";
						break;
        			case "卫生和社会工作":
						industryNew="Q";
						break;
        			case "文化、体育和娱乐业":
						industryNew="R";
						break;
        			case "公共管理、社会保障和社会组织":
						industryNew="S";
						break;
        			case "国际组织":
						industryNew="T";
						break;
        			}
        			return industryNew;
    }
    public int areaConvert(String area){
		int areaNew=140000;
			switch (area) {
					case"地区":
						areaNew=140000;
						break;
					case "全部":
						areaNew=Integer.parseInt("");
						break;
					case "北京市":
						areaNew=110000;
						break;
					case "天津市":
						areaNew=120000;
						break;
					case "河北省":
						areaNew=130000;
						break;
					case "山西省":
						areaNew=140000;
						break;
					case "内蒙古自治区":
						areaNew=150000;
						break;
					case "辽宁省":
						areaNew=210000;
						break;
					case "吉林省":
						areaNew=220000;
						break;
					case "黑龙江省":
						areaNew=230000;
						break;
					case "上海市":
						areaNew=310000;
						break;
					case "江苏省":
						areaNew=320000;
						break;
					case "浙江省":
						areaNew=330000;
						break;
					case "安徽省":
						areaNew=340000;
						break;
					case "福建省":
						areaNew=350000;
						break;
					case "江西省":
						areaNew=360000;
						break;
					case "山东省":
						areaNew=370000;
						break;
					case "河南省":
						areaNew=410000;
						break;
					case "湖北省":
						areaNew=420000;
						break;
					case "湖南省":
						areaNew=430000;
						break;
					case "广东省":
						areaNew=440000;
						break;
					case "广西壮族自治区":
						areaNew=450000;
						break;
					case "重庆市":
						areaNew=500000;
						break;
					case "四川省":
						areaNew=510000;
						break;
					case "贵州省":
						areaNew=520000;
						break;
					case "云南省":
						areaNew=530000;
						break;
					case "西藏自治区":
						areaNew=540000;
						break;
					case "陕西省":
						areaNew=610000;
						break;
					case "甘肃省":
						areaNew=620000;
						break;
					case "青海省":
						areaNew=630000;
						break;
					case "宁夏回族自治区":
						areaNew=640000;
						break;
					case "新疆维吾尔自治区":
						areaNew=650000;
						break;
					case "台湾省":
						areaNew=710000;
						break;
					case "香港特别行政区":
						areaNew=810000;
						break;
					case "澳门特别行政区":
						areaNew=140000;
						break;
					default:
						break;
					}
					return areaNew;
	}
	public int timeConvert(String time){
		int timeNew=0;
			switch (time) {
				    case "时间":
				    	timeNew=0;
						break;
				    case "全部":
						timeNew=0;
						break;
					case "最近一周":
						timeNew=1;
						break;
					case "最近一月":
						timeNew=2;
						break;
					case "最近半年":
						timeNew=3;
						break;
					default:
						break;
					}
					return timeNew;
	}
}
