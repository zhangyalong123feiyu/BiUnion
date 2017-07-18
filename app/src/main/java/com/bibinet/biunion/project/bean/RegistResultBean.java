package com.bibinet.biunion.project.bean;

import java.io.Serializable;

/**
 * Created by bibinet on 2017-7-14.
 */

public class RegistResultBean implements Serializable{

    /**
     * resCode : 0000
     * resMessage : 操作成功
     * user : {"image":"","enterprise":{"businessLicenseCardNo":"","enterpriseCode":"100520","businessLicenseName":"","contactName":"sdaf","industry":"","contactCellphone":"18295715877","originalFileInfoId":"","USCCode":"","thumbnailFileInfoId":"","region":"","addr":"","enterpriseName":"dsaf","tradingCertificateURL":""},"emchatUserName":"BIBENET100168","name":"dsaf","emchatPassword":1500024284000,"userId":"101070"}
     * registForm : {"enterpriseName":"dsaf","accountSuffix":"-1","userName":"sdaf","cellPhone":"18295715877","systemId":1005}
     */

    private String resCode;
    private String resMessage;
    private UserBean user;
    private RegistFormBean registForm;

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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public RegistFormBean getRegistForm() {
        return registForm;
    }

    public void setRegistForm(RegistFormBean registForm) {
        this.registForm = registForm;
    }

    public static class UserBean {
        /**
         * image :
         * enterprise : {"businessLicenseCardNo":"","enterpriseCode":"100520","businessLicenseName":"","contactName":"sdaf","industry":"","contactCellphone":"18295715877","originalFileInfoId":"","USCCode":"","thumbnailFileInfoId":"","region":"","addr":"","enterpriseName":"dsaf","tradingCertificateURL":""}
         * emchatUserName : BIBENET100168
         * name : dsaf
         * emchatPassword : 1500024284000
         * userId : 101070
         */

        private String image;
        private EnterpriseBean enterprise;
        private String emchatUserName;
        private String name;
        private long emchatPassword;
        private String userId;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public EnterpriseBean getEnterprise() {
            return enterprise;
        }

        public void setEnterprise(EnterpriseBean enterprise) {
            this.enterprise = enterprise;
        }

        public String getEmchatUserName() {
            return emchatUserName;
        }

        public void setEmchatUserName(String emchatUserName) {
            this.emchatUserName = emchatUserName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getEmchatPassword() {
            return emchatPassword;
        }

        public void setEmchatPassword(long emchatPassword) {
            this.emchatPassword = emchatPassword;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public static class EnterpriseBean {
            /**
             * businessLicenseCardNo :
             * enterpriseCode : 100520
             * businessLicenseName :
             * contactName : sdaf
             * industry :
             * contactCellphone : 18295715877
             * originalFileInfoId :
             * USCCode :
             * thumbnailFileInfoId :
             * region :
             * addr :
             * enterpriseName : dsaf
             * tradingCertificateURL :
             */

            private String businessLicenseCardNo;
            private String enterpriseCode;
            private String businessLicenseName;
            private String contactName;
            private String industry;
            private String contactCellphone;
            private String originalFileInfoId;
            private String USCCode;
            private String thumbnailFileInfoId;
            private String region;
            private String addr;
            private String enterpriseName;
            private String tradingCertificateURL;

            public String getBusinessLicenseCardNo() {
                return businessLicenseCardNo;
            }

            public void setBusinessLicenseCardNo(String businessLicenseCardNo) {
                this.businessLicenseCardNo = businessLicenseCardNo;
            }

            public String getEnterpriseCode() {
                return enterpriseCode;
            }

            public void setEnterpriseCode(String enterpriseCode) {
                this.enterpriseCode = enterpriseCode;
            }

            public String getBusinessLicenseName() {
                return businessLicenseName;
            }

            public void setBusinessLicenseName(String businessLicenseName) {
                this.businessLicenseName = businessLicenseName;
            }

            public String getContactName() {
                return contactName;
            }

            public void setContactName(String contactName) {
                this.contactName = contactName;
            }

            public String getIndustry() {
                return industry;
            }

            public void setIndustry(String industry) {
                this.industry = industry;
            }

            public String getContactCellphone() {
                return contactCellphone;
            }

            public void setContactCellphone(String contactCellphone) {
                this.contactCellphone = contactCellphone;
            }

            public String getOriginalFileInfoId() {
                return originalFileInfoId;
            }

            public void setOriginalFileInfoId(String originalFileInfoId) {
                this.originalFileInfoId = originalFileInfoId;
            }

            public String getUSCCode() {
                return USCCode;
            }

            public void setUSCCode(String USCCode) {
                this.USCCode = USCCode;
            }

            public String getThumbnailFileInfoId() {
                return thumbnailFileInfoId;
            }

            public void setThumbnailFileInfoId(String thumbnailFileInfoId) {
                this.thumbnailFileInfoId = thumbnailFileInfoId;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getEnterpriseName() {
                return enterpriseName;
            }

            public void setEnterpriseName(String enterpriseName) {
                this.enterpriseName = enterpriseName;
            }

            public String getTradingCertificateURL() {
                return tradingCertificateURL;
            }

            public void setTradingCertificateURL(String tradingCertificateURL) {
                this.tradingCertificateURL = tradingCertificateURL;
            }
        }
    }

    public static class RegistFormBean {
        /**
         * enterpriseName : dsaf
         * accountSuffix : -1
         * userName : sdaf
         * cellPhone : 18295715877
         * systemId : 1005
         */

        private String enterpriseName;
        private String accountSuffix;
        private String userName;
        private String cellPhone;
        private int systemId;

        public String getEnterpriseName() {
            return enterpriseName;
        }

        public void setEnterpriseName(String enterpriseName) {
            this.enterpriseName = enterpriseName;
        }

        public String getAccountSuffix() {
            return accountSuffix;
        }

        public void setAccountSuffix(String accountSuffix) {
            this.accountSuffix = accountSuffix;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCellPhone() {
            return cellPhone;
        }

        public void setCellPhone(String cellPhone) {
            this.cellPhone = cellPhone;
        }

        public int getSystemId() {
            return systemId;
        }

        public void setSystemId(int systemId) {
            this.systemId = systemId;
        }
    }
}
