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
     * page : {"pageNum":1,"pageSize":9}
     * items : [{"projectLocation":"内蒙古自治区","projectPublishTime":"发布时间：2017-05-13","projectUrl":"/pis/biddingInfos/toDetail.htm?code=2227b29e60c6448ea3977e709c271304","projectTypeName":"工程","projectCode":"2227b29e60c6448ea3977e709c271304","projectAmount":"","projectType":"A","projectName":"招标公告0513","projectTime":"已截止","projectDescrp":"公告摘要***********招标公司受业主*******委托，于2017年05月13日在中国招标网发布三亚市创意产业园3号路A段道路工程延长段项目监理。各有关单位请于2017.06.06前与公告中联系人联系，及时参与投标等相关工作，以免错失商业机会。部分信息内容如下：（查看详细...","projectTitle":"招标公告"}]
     */

    private String resCode;
    private String resMessage;
    private PageBean page;
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

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class PageBean {
        /**
         * pageNum : 1
         * pageSize : 9
         */

        private int pageNum;
        private int pageSize;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }

    public static class ItemsBean {
        /**
         * projectLocation : 内蒙古自治区
         * projectPublishTime : 发布时间：2017-05-13
         * projectUrl : /pis/biddingInfos/toDetail.htm?code=2227b29e60c6448ea3977e709c271304
         * projectTypeName : 工程
         * projectCode : 2227b29e60c6448ea3977e709c271304
         * projectAmount :
         * projectType : A
         * projectName : 招标公告0513
         * projectTime : 已截止
         * projectDescrp : 公告摘要***********招标公司受业主*******委托，于2017年05月13日在中国招标网发布三亚市创意产业园3号路A段道路工程延长段项目监理。各有关单位请于2017.06.06前与公告中联系人联系，及时参与投标等相关工作，以免错失商业机会。部分信息内容如下：（查看详细...
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
