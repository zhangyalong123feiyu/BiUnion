package com.bibinet.biunion.project.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bibinet on 2017-6-26.
 */

public class BannerBean implements Serializable {

    /**
     * resCode : 0000
     * resMessage : 操作成功
     * item : [{"imgUrl":"http://www.bibenet.com/iip-portlet/review?filePath=20175/6/14/34c885a0-e279-46f1-a6d7-cbd89a5e55a8.png","pageUrl":"http://www.bibenet.com/information_detail?code=6f3dac075444445e8da5ee3e67ae0be8&infoType=2"},{"imgUrl":"http://www.bibenet.com/iip-portlet/review?filePath=20174/25/18/1805b048-792b-4bea-813f-6da23ea61e78.png","pageUrl":""},{"imgUrl":"http://www.bibenet.com/iip-portlet/review?filePath=20175/6/14/2b5fc0a0-ef86-4ac5-b33c-692b7901f394.jpg","pageUrl":"http://www.bibenet.com/information_detail?code=e2a7c74c0ce94d878f25b59ae0b11666&infoType=2"},{"imgUrl":"http://www.bibenet.com/iip-portlet/images/banner/a1-04.png","pageUrl":""}]
     */

    private String resCode;
    private String resMessage;
    private List<ItemBean> item;

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

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public static class ItemBean {
        /**
         * imgUrl : http://www.bibenet.com/iip-portlet/review?filePath=20175/6/14/34c885a0-e279-46f1-a6d7-cbd89a5e55a8.png
         * pageUrl : http://www.bibenet.com/information_detail?code=6f3dac075444445e8da5ee3e67ae0be8&infoType=2
         */

        private String imgUrl;
        private String pageUrl;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getPageUrl() {
            return pageUrl;
        }

        public void setPageUrl(String pageUrl) {
            this.pageUrl = pageUrl;
        }
    }
}
