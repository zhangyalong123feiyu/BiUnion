package com.bibinet.biunion.project.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bibinet on 2017-6-7.
 */

public class ProjectInfoBean implements Serializable {

    /**
     * purchaseQueryFilter : {"publishDateEnd":"2017-06-22","type":1,"realAnnouncementType":"","bidEndTimeStr":"","summary":""}
     * resCode : 0000
     * resMessage : 操作成功
     * page : {"pageNum":1,"pageSize":8,"totalPage":3,"totalRows":21,"lastPage":1,"nextPage":2,"startRow":1,"endRow":8}
     * items : [{"projectLocation":"北京市","projectPublishTime":"发布时间：2017-06-16","projectUrl":"/pis/purchaseInfos/toDetail.htm?code=907585f4f95c4b2996feb03d3a1e13d8","projectTypeName":"货物","projectCode":"907585f4f95c4b2996feb03d3a1e13d8","projectAmount":"","projectType":"B","projectName":"z","projectTime":"2017-06-16","projectDescrp":"Z123","projectTitle":"招标公告"},{"projectLocation":"北京市","projectPublishTime":"发布时间：2017-06-16","projectUrl":"/pis/purchaseInfos/toDetail.htm?code=434096d5a9c74515b5724a2c35388d4a","projectTypeName":"工程","projectCode":"434096d5a9c74515b5724a2c35388d4a","projectAmount":"","projectType":"A","projectName":"政府采购","projectTime":"2017-06-16","projectDescrp":"13123123123","projectTitle":"招标公告"},{"projectLocation":"山西省","projectPublishTime":"发布时间：2017-06-12","projectUrl":"/pis/purchaseInfos/toDetail.htm?code=2808abc1a48c4633aaf1d4229824131b","projectTypeName":"工程","projectCode":"2808abc1a48c4633aaf1d4229824131b","projectAmount":"","projectType":"A","projectName":"cs","projectTime":"2017-06-12","projectDescrp":"ca","projectTitle":"结果公告"},{"projectLocation":"北京市","projectPublishTime":"发布时间：2017-04-19","projectUrl":"/pis/purchaseInfos/toDetail.htm?code=25ef6d2faf854ac2852e708c5ff222ff","projectTypeName":"工程","projectCode":"25ef6d2faf854ac2852e708c5ff222ff","projectAmount":"","projectType":"A","projectName":"3","projectTime":"2017-04-19","projectDescrp":"发的说法","projectTitle":"结果公告"},{"projectLocation":"河北省","projectPublishTime":"发布时间：2017-04-19","projectUrl":"/pis/purchaseInfos/toDetail.htm?code=132a32a7631d48179b1faccce127c47b","projectTypeName":"货物","projectCode":"132a32a7631d48179b1faccce127c47b","projectAmount":"","projectType":"B","projectName":"2","projectTime":"2017-04-19","projectDescrp":"委屈委屈我是的","projectTitle":"变更公告"},{"projectLocation":"山西省","projectPublishTime":"发布时间：2017-04-19","projectUrl":"/pis/purchaseInfos/toDetail.htm?code=1aa2ab58b1354b5e9c03d974594c74a2","projectTypeName":"工程","projectCode":"1aa2ab58b1354b5e9c03d974594c74a2","projectAmount":"","projectType":"A","projectName":"1","projectTime":"2017-04-19","projectDescrp":"额外热污染我","projectTitle":"招标公告"},{"projectLocation":"天津市","projectPublishTime":"发布时间：2017-04-19","projectUrl":"/pis/purchaseInfos/toDetail.htm?code=999ff6f643804c0a821c399cebcf0f2a","projectTypeName":"货物","projectCode":"999ff6f643804c0a821c399cebcf0f2a","projectAmount":"3.23万元","projectType":"B","projectName":"高鸿股份","projectTime":"2017-04-19","projectDescrp":"开机后付款的恢复会计师的恢复快较好的是尽快恢复快接收到","projectTitle":"招标公告"},{"projectLocation":"河北省","projectPublishTime":"发布时间：2017-04-19","projectUrl":"/pis/purchaseInfos/toDetail.htm?code=1aa1bdd2fae94e99bcf8c14d4366bb23","projectTypeName":"工程","projectCode":"1aa1bdd2fae94e99bcf8c14d4366bb23","projectAmount":"","projectType":"A","projectName":"置顶爬虫","projectTime":"2017-04-19","projectDescrp":"离开的减肥了会计师的咖啡就流口水的健康傅雷家书抵抗力就分开了的设计费","projectTitle":"招标公告"}]
     */

    private PurchaseQueryFilterBean purchaseQueryFilter;
    private String resCode;
    private String resMessage;
    private List<ItemsBean> items;

    public PurchaseQueryFilterBean getPurchaseQueryFilter() {
        return purchaseQueryFilter;
    }

    public void setPurchaseQueryFilter(PurchaseQueryFilterBean purchaseQueryFilter) {
        this.purchaseQueryFilter = purchaseQueryFilter;
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

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class PurchaseQueryFilterBean {
        /**
         * publishDateEnd : 2017-06-22
         * type : 1
         * realAnnouncementType :
         * bidEndTimeStr :
         * summary :
         */

        private String publishDateEnd;
        private int type;
        private String realAnnouncementType;
        private String bidEndTimeStr;
        private String summary;

        public String getPublishDateEnd() {
            return publishDateEnd;
        }

        public void setPublishDateEnd(String publishDateEnd) {
            this.publishDateEnd = publishDateEnd;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getRealAnnouncementType() {
            return realAnnouncementType;
        }

        public void setRealAnnouncementType(String realAnnouncementType) {
            this.realAnnouncementType = realAnnouncementType;
        }

        public String getBidEndTimeStr() {
            return bidEndTimeStr;
        }

        public void setBidEndTimeStr(String bidEndTimeStr) {
            this.bidEndTimeStr = bidEndTimeStr;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
    }

    public static class ItemsBean {
        /**
         * projectLocation : 北京市
         * projectPublishTime : 发布时间：2017-06-16
         * projectUrl : /pis/purchaseInfos/toDetail.htm?code=907585f4f95c4b2996feb03d3a1e13d8
         * projectTypeName : 货物
         * projectCode : 907585f4f95c4b2996feb03d3a1e13d8
         * projectAmount :
         * projectType : B
         * projectName : z
         * projectTime : 2017-06-16
         * projectDescrp : Z123
         * projectTitle : 招标公告
         */

        private String projectLocation;
        private String projectPublishTime;
        private String projectUrl;
        private String projectTypeName;
        private String projectCode;
        private String projectAmount;
        private String projectType;
        private String projectName;
        private String projectTime;
        private String projectDescrp;
        private String projectTitle;

        public String getProjectLocation() {
            return projectLocation;
        }

        public void setProjectLocation(String projectLocation) {
            this.projectLocation = projectLocation;
        }

        public String getProjectPublishTime() {
            return projectPublishTime;
        }

        public void setProjectPublishTime(String projectPublishTime) {
            this.projectPublishTime = projectPublishTime;
        }

        public String getProjectUrl() {
            return projectUrl;
        }

        public void setProjectUrl(String projectUrl) {
            this.projectUrl = projectUrl;
        }

        public String getProjectTypeName() {
            return projectTypeName;
        }

        public void setProjectTypeName(String projectTypeName) {
            this.projectTypeName = projectTypeName;
        }

        public String getProjectCode() {
            return projectCode;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }

        public String getProjectAmount() {
            return projectAmount;
        }

        public void setProjectAmount(String projectAmount) {
            this.projectAmount = projectAmount;
        }

        public String getProjectType() {
            return projectType;
        }

        public void setProjectType(String projectType) {
            this.projectType = projectType;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getProjectTime() {
            return projectTime;
        }

        public void setProjectTime(String projectTime) {
            this.projectTime = projectTime;
        }

        public String getProjectDescrp() {
            return projectDescrp;
        }

        public void setProjectDescrp(String projectDescrp) {
            this.projectDescrp = projectDescrp;
        }

        public String getProjectTitle() {
            return projectTitle;
        }

        public void setProjectTitle(String projectTitle) {
            this.projectTitle = projectTitle;
        }
    }
}
