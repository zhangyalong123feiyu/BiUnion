package com.bibinet.biunion.project.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bibinet on 2017-6-26.
 */

public class WriteTenderBookHistoryBean implements Serializable {

    /**
     * resCode : 0000
     * resMessage : 操作成功
     * item : [{"objectId":100004,"code":"ad8d5cd336ba4cc98452b086292d3fff","tenderSelection":1,"projectType":0,"tenderMode":1,"tenderType":1,"contact":"AMY","customerId":100155,"cellPhone":"18295715877","email":"851031486@qq.com","createDate":1498459527000,"updateDate":1498459599000,"isEnd":2,"customerServiceId":100100,"isDelete":0}]
     * page : {"pageNum":1,"pageSize":8,"totalPage":1,"totalRows":1,"lastPage":1,"nextPage":1,"startRow":1,"endRow":1}
     * proxyTenderQueryFilter : {"cellPhone":"18295715877"}
     */

    private String resCode;
    private String resMessage;
    private ProxyTenderQueryFilterBean proxyTenderQueryFilter;
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


    public ProxyTenderQueryFilterBean getProxyTenderQueryFilter() {
        return proxyTenderQueryFilter;
    }

    public void setProxyTenderQueryFilter(ProxyTenderQueryFilterBean proxyTenderQueryFilter) {
        this.proxyTenderQueryFilter = proxyTenderQueryFilter;
    }

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }


    public static class ProxyTenderQueryFilterBean {
        /**
         * cellPhone : 18295715877
         */

        private String cellPhone;

        public String getCellPhone() {
            return cellPhone;
        }

        public void setCellPhone(String cellPhone) {
            this.cellPhone = cellPhone;
        }
    }

    public static class ItemBean {
        /**
         * objectId : 100004
         * code : ad8d5cd336ba4cc98452b086292d3fff
         * tenderSelection : 1
         * projectType : 0
         * tenderMode : 1
         * tenderType : 1
         * contact : AMY
         * customerId : 100155
         * cellPhone : 18295715877
         * email : 851031486@qq.com
         * createDate : 1498459527000
         * updateDate : 1498459599000
         * isEnd : 2
         * customerServiceId : 100100
         * isDelete : 0
         */
        private int objectId;
        private String code;
        private String tenderSelection;
        private String projectType;
        private String tenderMode;
        private String tenderType;
        private String contact;
        private int customerId;
        private String cellPhone;
        private String email;
        private String createDate;
        private String updateDate;
        private String isEnd;
        private String customerServiceId;
        private String isDelete;

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

        public String getTenderSelection() {
            return tenderSelection;
        }

        public void setTenderSelection(String tenderSelection) {
            this.tenderSelection = tenderSelection;
        }

        public String getProjectType() {
            return projectType;
        }

        public void setProjectType(String projectType) {
            this.projectType = projectType;
        }

        public String getTenderMode() {
            return tenderMode;
        }

        public void setTenderMode(String tenderMode) {
            this.tenderMode = tenderMode;
        }

        public String getTenderType() {
            return tenderType;
        }

        public void setTenderType(String tenderType) {
            this.tenderType = tenderType;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getIsEnd() {
            return isEnd;
        }

        public void setIsEnd(String isEnd) {
            this.isEnd = isEnd;
        }

        public String getCustomerServiceId() {
            return customerServiceId;
        }

        public void setCustomerServiceId(String customerServiceId) {
            this.customerServiceId = customerServiceId;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }
    }
}
