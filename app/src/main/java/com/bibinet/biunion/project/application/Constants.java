package com.bibinet.biunion.project.application;

import com.bibinet.biunion.project.bean.LoginResultBean;

/**
 * Created by bibinet on 2017-5-18.
 */

public class Constants {
    public static final int READPHONE_STATE=1;
    public static LoginResultBean loginresultInfo=null;
    public static String baseUrl="http://192.168.185.185:8080/pis";
    public static String loginData=null;
    public static String[] ImageUrls={"http://www.bibenet.com/iip-portlet/review?filePath=20175/5/9/31b74a24-3bd5-4ebb-974d-1578709bb0da.jpg",
            "http://www.bibenet.com/iip-portlet/review?filePath=20174/25/18/1805b048-792b-4bea-813f-6da23ea61e78.png",
            "http://www.bibenet.com/iip-portlet/images/banner/a1-03.png",
            "http://www.bibenet.com/iip-portlet/review?filePath=20174/26/14/ebfd72c5-b14a-47eb-a09a-dc20cae33535.png"
    };
    public static String tenderProjectUrl="http://192.168.1.74:8080/pis/rest/biddingInfos/selectPage.json?";
    public static String projectDataUrl="http://192.168.1.74:8080/pis/rest/generalProjects/selectPage.json?";
    public static String buyProjectUrl="http://192.168.1.74:8080/pis/rest/purchaseInfos/selectPage.json?";
    public static String pProjectUrl="http://192.168.1.74:8080/pis/rest/pppProjects/selectPage.json?";
    public static String applayProjectUrl="http://192.168.1.74:8080/pis/rest/portalUsers/selectPage.json?";

}
