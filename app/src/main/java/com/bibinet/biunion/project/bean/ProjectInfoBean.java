package com.bibinet.biunion.project.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bibinet on 2017-6-7.
 */

public class ProjectInfoBean implements Serializable {


    /**
     * resCode : 0000
     * resMessage : 操作成功
     * page : {"pageNum":2,"pageSize":8,"totalPage":32,"totalRows":256,"lastPage":1,"nextPage":3,"startRow":9,"endRow":16}
     * items : [{"ProjectType":"","ProjectLocation":"天津市","ProjectUrl":"/pis/biddingInfos/toDetail.htm?code=A5139","ProjectTime":"","ProjectName":"gfhfh","ProjectDescrp":"hgjfvgkygyukfjymkr","ProjectCode":"A5139"},{"ProjectType":"","ProjectLocation":"天津市","ProjectUrl":"/pis/biddingInfos/toDetail.htm?code=7a7cf2ccaac94452bfc8ba07bb6324e6","ProjectTime":"已截止","ProjectName":"cs测试是否相同21","ProjectDescrp":"cs","ProjectCode":"7a7cf2ccaac94452bfc8ba07bb6324e6"},{"ProjectType":"","ProjectLocation":"北京市","ProjectUrl":"/pis/biddingInfos/toDetail.htm?code=03e8933c4d5a401f9ddcfb3dea180d49","ProjectTime":"已截止","ProjectName":"cs测试是否相同2","ProjectDescrp":"cs","ProjectCode":"03e8933c4d5a401f9ddcfb3dea180d49"},{"ProjectType":"","ProjectLocation":"山西省","ProjectUrl":"/pis/biddingInfos/toDetail.htm?code=bcb06b7984454effbcfd536c018ca8d6","ProjectTime":"已截止","ProjectName":"cs","ProjectDescrp":"cs","ProjectCode":"bcb06b7984454effbcfd536c018ca8d6"},{"ProjectType":"","ProjectLocation":"河北省","ProjectUrl":"/pis/biddingInfos/toDetail.htm?code=a91b3c4233ac4058810225bc16d5117d","ProjectTime":"已截止","ProjectName":"cs测试是否相同211","ProjectDescrp":"cs","ProjectCode":"a91b3c4233ac4058810225bc16d5117d"},{"ProjectType":"","ProjectLocation":"天津市","ProjectUrl":"/pis/biddingInfos/toDetail.htm?code=ece869960ac34dd998891422e5e159a9","ProjectTime":"已截止","ProjectName":"cs","ProjectDescrp":"cs","ProjectCode":"ece869960ac34dd998891422e5e159a9"},{"ProjectType":"","ProjectLocation":"山西省","ProjectUrl":"/pis/biddingInfos/toDetail.htm?code=A5136","ProjectTime":"46天7小时","ProjectName":"王彦强测试附件推送1","ProjectDescrp":"dfffaa发多少地方","ProjectCode":"A5136"},{"ProjectType":"","ProjectLocation":"山西省","ProjectUrl":"/pis/biddingInfos/toDetail.htm?code=A5137","ProjectTime":"17天17小时","ProjectName":"【变更公告】测试-不报名-服务0607","ProjectDescrp":"测试-不报名-服务0607","ProjectCode":"A5137"}]
     * biddingInfoQueryFilter : {"type":1,"publisherName":"比比网络","bidEndTimeStr":"","summary":"","realType":"招标公告"}
     */

    private String resCode;
    private String resMessage;
    private BiddingInfoQueryFilterBean biddingInfoQueryFilter;
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


    public BiddingInfoQueryFilterBean getBiddingInfoQueryFilter() {
        return biddingInfoQueryFilter;
    }

    public void setBiddingInfoQueryFilter(BiddingInfoQueryFilterBean biddingInfoQueryFilter) {
        this.biddingInfoQueryFilter = biddingInfoQueryFilter;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }


    public static class BiddingInfoQueryFilterBean {
        /**
         * type : 1
         * publisherName : 比比网络
         * bidEndTimeStr :
         * summary :
         * realType : 招标公告
         */

        private int type;
        private String publisherName;
        private String bidEndTimeStr;
        private String summary;
        private String realType;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getPublisherName() {
            return publisherName;
        }

        public void setPublisherName(String publisherName) {
            this.publisherName = publisherName;
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

        public String getRealType() {
            return realType;
        }

        public void setRealType(String realType) {
            this.realType = realType;
        }
    }

    public static class ItemsBean {
        /**
         * ProjectType :
         * ProjectLocation : 天津市
         * ProjectUrl : /pis/biddingInfos/toDetail.htm?code=A5139
         * ProjectTime :
         * ProjectName : gfhfh
         * ProjectDescrp : hgjfvgkygyukfjymkr
         * ProjectCode : A5139
         */
        private String ProjectType;
        private String ProjectLocation;
        private String ProjectUrl;
        private String ProjectTime;
        private String ProjectName;
        private String ProjectDescrp;
        private String ProjectCode;

        public String getProjectType() {
            return ProjectType;
        }

        public void setProjectType(String ProjectType) {
            this.ProjectType = ProjectType;
        }

        public String getProjectLocation() {
            return ProjectLocation;
        }

        public void setProjectLocation(String ProjectLocation) {
            this.ProjectLocation = ProjectLocation;
        }

        public String getProjectUrl() {
            return ProjectUrl;
        }

        public void setProjectUrl(String ProjectUrl) {
            this.ProjectUrl = ProjectUrl;
        }

        public String getProjectTime() {
            return ProjectTime;
        }

        public void setProjectTime(String ProjectTime) {
            this.ProjectTime = ProjectTime;
        }

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String ProjectName) {
            this.ProjectName = ProjectName;
        }

        public String getProjectDescrp() {
            return ProjectDescrp;
        }

        public void setProjectDescrp(String ProjectDescrp) {
            this.ProjectDescrp = ProjectDescrp;
        }

        public String getProjectCode() {
            return ProjectCode;
        }

        public void setProjectCode(String ProjectCode) {
            this.ProjectCode = ProjectCode;
        }
    }
}
