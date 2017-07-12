package com.bibinet.biunion.project.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bibinet on 2017-7-3.
 */

public class ExpertsAskAnswerResultBean implements Serializable {

    /**
     * resCode : 0000
     * resMessage : 操作成功
     * questionQueryFilter : {"page":{"pageNum":1,"pageSize":8},"summary":""}
     * page : {"pageNum":1,"pageSize":8}
     * items : [{"objectId":100057,"code":"56f486655cc94662a90f18c47ee159b7","createDate":1496972816000,"lastUpdateDate":1496972816000,"isDelete":0,"lastUpdaterId":101154,"content":"项目施工总承包给一家公司，该公司将项目的钢结构工程、玻璃幕墙等工程分包给了相应有资质的单位。该施工单位的做法合不合法？","publishDate":1496972816000,"accessCount":38,"summary":"项目施工总承包给一家公司，该公司将项目的钢结构工程、玻璃幕墙等工程分包给了相应有资质的单位。该施工单位的做法合不合法？","type":1,"questionerName":"伊新电气股份有限公司","creator":101154,"isShield":0,"stauts":1,"publisher":"伊新电气股份有限公司","title":"施工总承包单位是否可以将专业工程承包给其他单位","answerNumber":1,"replyNumber":0,"isSolved":0,"enterpriseId":100396}]
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
         * objectId : 100057
         * code : 56f486655cc94662a90f18c47ee159b7
         * createDate : 1496972816000
         * lastUpdateDate : 1496972816000
         * isDelete : 0
         * lastUpdaterId : 101154
         * content : 项目施工总承包给一家公司，该公司将项目的钢结构工程、玻璃幕墙等工程分包给了相应有资质的单位。该施工单位的做法合不合法？
         * publishDate : 1496972816000
         * accessCount : 38
         * summary : 项目施工总承包给一家公司，该公司将项目的钢结构工程、玻璃幕墙等工程分包给了相应有资质的单位。该施工单位的做法合不合法？
         * type : 1
         * questionerName : 伊新电气股份有限公司
         * creator : 101154
         * isShield : 0
         * stauts : 1
         * publisher : 伊新电气股份有限公司
         * title : 施工总承包单位是否可以将专业工程承包给其他单位
         * answerNumber : 1
         * replyNumber : 0
         * isSolved : 0
         * enterpriseId : 100396
         */

        private int objectId;
        private String code;
        private long createDate;
        private long lastUpdateDate;
        private int isDelete;
        private int lastUpdaterId;
        private String content;
        private long publishDate;
        private int accessCount;
        private String summary;
        private int type;
        private String questionerName;
        private int creator;
        private int isShield;
        private int stauts;
        private String publisher;
        private String title;
        private int answerNumber;
        private int replyNumber;
        private int isSolved;
        private int enterpriseId;

        public int getObjectId() {
            return objectId;
        }

        public void setObjectId(int objectId) {
            this.objectId = objectId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public long getLastUpdateDate() {
            return lastUpdateDate;
        }

        public void setLastUpdateDate(long lastUpdateDate) {
            this.lastUpdateDate = lastUpdateDate;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public int getLastUpdaterId() {
            return lastUpdaterId;
        }

        public void setLastUpdaterId(int lastUpdaterId) {
            this.lastUpdaterId = lastUpdaterId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(long publishDate) {
            this.publishDate = publishDate;
        }

        public int getAccessCount() {
            return accessCount;
        }

        public void setAccessCount(int accessCount) {
            this.accessCount = accessCount;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getQuestionerName() {
            return questionerName;
        }

        public void setQuestionerName(String questionerName) {
            this.questionerName = questionerName;
        }

        public int getCreator() {
            return creator;
        }

        public void setCreator(int creator) {
            this.creator = creator;
        }

        public int getIsShield() {
            return isShield;
        }

        public void setIsShield(int isShield) {
            this.isShield = isShield;
        }

        public int getStauts() {
            return stauts;
        }

        public void setStauts(int stauts) {
            this.stauts = stauts;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getAnswerNumber() {
            return answerNumber;
        }

        public void setAnswerNumber(int answerNumber) {
            this.answerNumber = answerNumber;
        }

        public int getReplyNumber() {
            return replyNumber;
        }

        public void setReplyNumber(int replyNumber) {
            this.replyNumber = replyNumber;
        }

        public int getIsSolved() {
            return isSolved;
        }

        public void setIsSolved(int isSolved) {
            this.isSolved = isSolved;
        }

        public int getEnterpriseId() {
            return enterpriseId;
        }

        public void setEnterpriseId(int enterpriseId) {
            this.enterpriseId = enterpriseId;
        }
    }
}
