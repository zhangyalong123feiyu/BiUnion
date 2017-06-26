package com.bibinet.biunion.project.application;

import com.bibinet.biunion.project.bean.LoginResultBean;

/**
 * Created by bibinet on 2017-5-18.
 */

public class Constants {
//   适配6.0所需权限
    public  final int LOCATION_PERMISSON=111;
    public  final int WRITESTORAGE_PERMISSON=112;
    public  final int READSTORAGE_PERMISSON=113;





    //APP所用常量
    public final int READPHONE_STATE = 1;
    public static LoginResultBean loginresultInfo = null;
    public static String baseUrl = "http://202.99.212.204:8080/";

    public static String loginData = null;
    public static String[] ImageUrls = {"http://www.bibenet.com/iip-portlet/review?filePath=20175/5/9/31b74a24-3bd5-4ebb-974d-1578709bb0da.jpg",
            "http://www.bibenet.com/iip-portlet/review?filePath=20174/25/18/1805b048-792b-4bea-813f-6da23ea61e78.png",
            "http://www.bibenet.com/iip-portlet/images/banner/a1-03.png",
            "http://www.bibenet.com/iip-portlet/review?filePath=20175/6/14/2b5fc0a0-ef86-4ac5-b33c-692b7901f394.jpg"
    };
    public static String tenderProjectUrl = "http://192.168.1.74:8080/pis/rest/biddingInfos/selectPage.json?";
    public static String projectDataUrl = "http://192.168.1.74:8080/pis/rest/generalProjects/selectPage.json?";
    public static String buyProjectUrl = "http://192.168.1.74:8080/pis/rest/purchaseInfos/selectPage.json?";
    public static String pProjectUrl = "http://192.168.1.74:8080/pis/rest/pppProjects/selectPage.json?";
    public static String applayProjectUrl = "http://192.168.1.74:8080/pis/rest/portalUsers/selectPage.json?";

}
