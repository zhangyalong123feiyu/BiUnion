package com.bibinet.biunion.project.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bibinet on 2017-6-20.
 */

public class SearchResultBean implements Serializable {

    /**
     * resCode : 0000
     * resMessage : 操作成功
     * page : {"pageNum":1,"pageSize":9,"totalPage":0,"totalRows":0,"lastPage":1,"nextPage":2}
     * items : [{"projectCode":"A4909","projectUrl":"/pis/biddingInfos/toDetail.htm?code=A4909","projectLocation":"山西省","projectType":"C","projectTime":"已截止","projectDescrp":"实施","projectName":"太原日报"}]
     */

    private String resCode;
    private String resMessage;
    private List<ItemsBean> items;

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

    public static class ItemsBean {
        /**
         * projectCode : A4909
         * projectUrl : /pis/biddingInfos/toDetail.htm?code=A4909
         * projectLocation : 山西省
         * projectType : C
         * projectTime : 已截止
         * projectDescrp : 实施
         * projectName : 太原日报
         */

        private String projectCode;
        private String projectUrl;
        private String projectLocation;
        private String projectType;
        private String projectTime;
        private String projectDescrp;
        private String projectName;

        public String getProjectCode() {
            return projectCode;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }

        public String getProjectUrl() {
            return projectUrl;
        }

        public void setProjectUrl(String projectUrl) {
            this.projectUrl = projectUrl;
        }

        public String getProjectLocation() {
            return projectLocation;
        }

        public void setProjectLocation(String projectLocation) {
            this.projectLocation = projectLocation;
        }

        public String getProjectType() {
            return projectType;
        }

        public void setProjectType(String projectType) {
            this.projectType = projectType;
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

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }
    }
}
