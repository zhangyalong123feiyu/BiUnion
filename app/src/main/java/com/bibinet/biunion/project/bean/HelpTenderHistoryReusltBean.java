package com.bibinet.biunion.project.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bibinet on 2017-6-29.
 */

public class HelpTenderHistoryReusltBean implements Serializable {

    /**
     * bidAssistanceQueryFilter : {"customerId":101346,"isDelete":0}
     * resCode : 0000
     * resMessage : 操作成功
     * item : [{"objectId":100001,"code":"952bfdaf320c44359d67119ccd76aab1","contact":"写一下","customerId":101346,"cellPhone":"18295715877","content":"呢莫","isEnd":0,"createDate":1498723069000,"isDelete":0},{"objectId":100002,"code":"8a0c06ac21814fd98f7f833e7927b5c4","contact":"写一下","customerId":101346,"cellPhone":"18295715877","content":"呢莫","isEnd":0,"createDate":1498723135000,"isDelete":0},{"objectId":100003,"code":"6920d5b49bba4840957643acba7677d5","contact":"写一下","customerId":101346,"cellPhone":"18295715877","content":"呢莫","isEnd":0,"createDate":1498723240000,"isDelete":0},{"objectId":100004,"code":"693ca58330314127840c536e30351507","contact":"写一下","customerId":101346,"cellPhone":"18295715877","content":"呢莫","isEnd":0,"createDate":1498723247000,"isDelete":0},{"objectId":100005,"code":"07f488ba6cab4bdbbc90a54899febcbc","contact":"写一下","customerId":101346,"cellPhone":"18295715877","content":"呢莫","isEnd":0,"createDate":1498723299000,"isDelete":0}]
     * page : {"pageNum":1,"pageSize":8,"totalPage":1,"totalRows":5,"lastPage":1,"nextPage":1,"startRow":1,"endRow":5}
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
         * objectId : 100001
         * code : 952bfdaf320c44359d67119ccd76aab1
         * contact : 写一下
         * customerId : 101346
         * cellPhone : 18295715877
         * content : 呢莫
         * isEnd : 0
         * createDate : 1498723069000
         * isDelete : 0
         */

        private int objectId;
        private String code;
        private String contact;
        private int customerId;
        private String cellPhone;
        private String content;
        private int isEnd;
        private long createDate;
        private int isDelete;

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

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public String getCellPhone() {
            return cellPhone;
        }

        public void setCellPhone(String cellPhone) {
            this.cellPhone = cellPhone;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIsEnd() {
            return isEnd;
        }

        public void setIsEnd(int isEnd) {
            this.isEnd = isEnd;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }
    }
}
