package com.bibinet.biunion.project.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bibinet on 2017-6-12.
 */

public class LoginResultBean implements Serializable{

    /**
     * resCode : 0000
     * resMessage : 操作成功
     * user : {"name":"魔方大厦","enterprise":{"objectId":100288,"code":"b607c4aa06164d36a61e6476b4e745ba","createDate":1490926866000,"lastUpdateDate":1491837467000,"customerId":100239,"name":"魔方大厦","accountSuffix":"mf","orgCode":"123","industry":"A","status":7,"businessLicenseName":"丁","businessLicenseCardNo":"140303199212040424","contactName":"丁","contactCellphone":"18235148818","shortName":"魔方","isCopy":0,"isDelete":0,"checkOptions":"456","checkPassDate":1491637695000,"address":{"objectId":100818,"enterpriseId":100288,"privinceId":110000,"cityId":110100,"countyId":110101,"addr":"小店","isDelete":0,"createDate":1491837467000,"lastUpdateDate":1491837467000},"certificates":[{"objectId":102690,"enterpriseId":100288,"name":"营业执照","type":1,"isDelete":0,"createDate":1491837467000,"lastUpdateDate":1491837467000,"originalFileInfoId":105399,"thumbnatlFileInfoId":105400,"originalFileInfo":{"objectId":105399,"fileName":"营业执照.jpg","extName":"jpg","serverId":"192.168.1.15:21","relativePath":"20173/1/16/edf8f6bb-ba23-4e1c-903d-2bdbf6eaf62e.jpg","createDate":1491753600000,"lastUpdateDate":1491753600000,"isDelete":0,"storeName":"edf8f6bb-ba23-4e1c-903d-2bdbf6eaf62e.jpg"},"thumbnailFileInfo":{"objectId":105400,"fileName":"营业执照.jpg","extName":"jpg","serverId":"192.168.1.15:21","relativePath":"20173/1/16/43874b45-a41b-4397-b4dc-426758dc6be1.jpg","createDate":1491753600000,"lastUpdateDate":1491753600000,"isDelete":0,"storeName":"43874b45-a41b-4397-b4dc-426758dc6be1.jpg"}}],"uscCode":"123","industrySecond":"1","industryThird":"11"},"image":"data:image/png;base64,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"}
     */

    private String resCode;
    private String resMessage;
    private UserBean user;

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

    public static class UserBean {
        /**
         * name : 魔方大厦
         * enterprise : {"objectId":100288,"code":"b607c4aa06164d36a61e6476b4e745ba","createDate":1490926866000,"lastUpdateDate":1491837467000,"customerId":100239,"name":"魔方大厦","accountSuffix":"mf","orgCode":"123","industry":"A","status":7,"businessLicenseName":"丁","businessLicenseCardNo":"140303199212040424","contactName":"丁","contactCellphone":"18235148818","shortName":"魔方","isCopy":0,"isDelete":0,"checkOptions":"456","checkPassDate":1491637695000,"address":{"objectId":100818,"enterpriseId":100288,"privinceId":110000,"cityId":110100,"countyId":110101,"addr":"小店","isDelete":0,"createDate":1491837467000,"lastUpdateDate":1491837467000},"certificates":[{"objectId":102690,"enterpriseId":100288,"name":"营业执照","type":1,"isDelete":0,"createDate":1491837467000,"lastUpdateDate":1491837467000,"originalFileInfoId":105399,"thumbnatlFileInfoId":105400,"originalFileInfo":{"objectId":105399,"fileName":"营业执照.jpg","extName":"jpg","serverId":"192.168.1.15:21","relativePath":"20173/1/16/edf8f6bb-ba23-4e1c-903d-2bdbf6eaf62e.jpg","createDate":1491753600000,"lastUpdateDate":1491753600000,"isDelete":0,"storeName":"edf8f6bb-ba23-4e1c-903d-2bdbf6eaf62e.jpg"},"thumbnailFileInfo":{"objectId":105400,"fileName":"营业执照.jpg","extName":"jpg","serverId":"192.168.1.15:21","relativePath":"20173/1/16/43874b45-a41b-4397-b4dc-426758dc6be1.jpg","createDate":1491753600000,"lastUpdateDate":1491753600000,"isDelete":0,"storeName":"43874b45-a41b-4397-b4dc-426758dc6be1.jpg"}}],"uscCode":"123","industrySecond":"1","industryThird":"11"}
         * image : data:image/png;base64,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
         */

        private String name;
        private EnterpriseBean enterprise;
        private String image;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public EnterpriseBean getEnterprise() {
            return enterprise;
        }

        public void setEnterprise(EnterpriseBean enterprise) {
            this.enterprise = enterprise;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public static class EnterpriseBean {
            /**
             * objectId : 100288
             * code : b607c4aa06164d36a61e6476b4e745ba
             * createDate : 1490926866000
             * lastUpdateDate : 1491837467000
             * customerId : 100239
             * name : 魔方大厦
             * accountSuffix : mf
             * orgCode : 123
             * industry : A
             * status : 7
             * businessLicenseName : 丁
             * businessLicenseCardNo : 140303199212040424
             * contactName : 丁
             * contactCellphone : 18235148818
             * shortName : 魔方
             * isCopy : 0
             * isDelete : 0
             * checkOptions : 456
             * checkPassDate : 1491637695000
             * address : {"objectId":100818,"enterpriseId":100288,"privinceId":110000,"cityId":110100,"countyId":110101,"addr":"小店","isDelete":0,"createDate":1491837467000,"lastUpdateDate":1491837467000}
             * certificates : [{"objectId":102690,"enterpriseId":100288,"name":"营业执照","type":1,"isDelete":0,"createDate":1491837467000,"lastUpdateDate":1491837467000,"originalFileInfoId":105399,"thumbnatlFileInfoId":105400,"originalFileInfo":{"objectId":105399,"fileName":"营业执照.jpg","extName":"jpg","serverId":"192.168.1.15:21","relativePath":"20173/1/16/edf8f6bb-ba23-4e1c-903d-2bdbf6eaf62e.jpg","createDate":1491753600000,"lastUpdateDate":1491753600000,"isDelete":0,"storeName":"edf8f6bb-ba23-4e1c-903d-2bdbf6eaf62e.jpg"},"thumbnailFileInfo":{"objectId":105400,"fileName":"营业执照.jpg","extName":"jpg","serverId":"192.168.1.15:21","relativePath":"20173/1/16/43874b45-a41b-4397-b4dc-426758dc6be1.jpg","createDate":1491753600000,"lastUpdateDate":1491753600000,"isDelete":0,"storeName":"43874b45-a41b-4397-b4dc-426758dc6be1.jpg"}}]
             * uscCode : 123
             * industrySecond : 1
             * industryThird : 11
             */

            private int objectId;
            private String code;
            private long createDate;
            private long lastUpdateDate;
            private int customerId;
            private String name;
            private String accountSuffix;
            private String orgCode;
            private String industry;
            private int status;
            private String businessLicenseName;
            private String businessLicenseCardNo;
            private String contactName;
            private String contactCellphone;
            private String shortName;
            private int isCopy;
            private int isDelete;
            private String checkOptions;
            private long checkPassDate;
            private AddressBean address;
            private String uscCode;
            private String industrySecond;
            private String industryThird;
            private List<CertificatesBean> certificates;

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

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAccountSuffix() {
                return accountSuffix;
            }

            public void setAccountSuffix(String accountSuffix) {
                this.accountSuffix = accountSuffix;
            }

            public String getOrgCode() {
                return orgCode;
            }

            public void setOrgCode(String orgCode) {
                this.orgCode = orgCode;
            }

            public String getIndustry() {
                return industry;
            }

            public void setIndustry(String industry) {
                this.industry = industry;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getBusinessLicenseName() {
                return businessLicenseName;
            }

            public void setBusinessLicenseName(String businessLicenseName) {
                this.businessLicenseName = businessLicenseName;
            }

            public String getBusinessLicenseCardNo() {
                return businessLicenseCardNo;
            }

            public void setBusinessLicenseCardNo(String businessLicenseCardNo) {
                this.businessLicenseCardNo = businessLicenseCardNo;
            }

            public String getContactName() {
                return contactName;
            }

            public void setContactName(String contactName) {
                this.contactName = contactName;
            }

            public String getContactCellphone() {
                return contactCellphone;
            }

            public void setContactCellphone(String contactCellphone) {
                this.contactCellphone = contactCellphone;
            }

            public String getShortName() {
                return shortName;
            }

            public void setShortName(String shortName) {
                this.shortName = shortName;
            }

            public int getIsCopy() {
                return isCopy;
            }

            public void setIsCopy(int isCopy) {
                this.isCopy = isCopy;
            }

            public int getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(int isDelete) {
                this.isDelete = isDelete;
            }

            public String getCheckOptions() {
                return checkOptions;
            }

            public void setCheckOptions(String checkOptions) {
                this.checkOptions = checkOptions;
            }

            public long getCheckPassDate() {
                return checkPassDate;
            }

            public void setCheckPassDate(long checkPassDate) {
                this.checkPassDate = checkPassDate;
            }

            public AddressBean getAddress() {
                return address;
            }

            public void setAddress(AddressBean address) {
                this.address = address;
            }

            public String getUscCode() {
                return uscCode;
            }

            public void setUscCode(String uscCode) {
                this.uscCode = uscCode;
            }

            public String getIndustrySecond() {
                return industrySecond;
            }

            public void setIndustrySecond(String industrySecond) {
                this.industrySecond = industrySecond;
            }

            public String getIndustryThird() {
                return industryThird;
            }

            public void setIndustryThird(String industryThird) {
                this.industryThird = industryThird;
            }

            public List<CertificatesBean> getCertificates() {
                return certificates;
            }

            public void setCertificates(List<CertificatesBean> certificates) {
                this.certificates = certificates;
            }

            public static class AddressBean {
                /**
                 * objectId : 100818
                 * enterpriseId : 100288
                 * privinceId : 110000
                 * cityId : 110100
                 * countyId : 110101
                 * addr : 小店
                 * isDelete : 0
                 * createDate : 1491837467000
                 * lastUpdateDate : 1491837467000
                 */

                private int objectId;
                private int enterpriseId;
                private int privinceId;
                private int cityId;
                private int countyId;
                private String addr;
                private int isDelete;
                private long createDate;
                private long lastUpdateDate;

                public int getObjectId() {
                    return objectId;
                }

                public void setObjectId(int objectId) {
                    this.objectId = objectId;
                }

                public int getEnterpriseId() {
                    return enterpriseId;
                }

                public void setEnterpriseId(int enterpriseId) {
                    this.enterpriseId = enterpriseId;
                }

                public int getPrivinceId() {
                    return privinceId;
                }

                public void setPrivinceId(int privinceId) {
                    this.privinceId = privinceId;
                }

                public int getCityId() {
                    return cityId;
                }

                public void setCityId(int cityId) {
                    this.cityId = cityId;
                }

                public int getCountyId() {
                    return countyId;
                }

                public void setCountyId(int countyId) {
                    this.countyId = countyId;
                }

                public String getAddr() {
                    return addr;
                }

                public void setAddr(String addr) {
                    this.addr = addr;
                }

                public int getIsDelete() {
                    return isDelete;
                }

                public void setIsDelete(int isDelete) {
                    this.isDelete = isDelete;
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
            }

            public static class CertificatesBean {
                /**
                 * objectId : 102690
                 * enterpriseId : 100288
                 * name : 营业执照
                 * type : 1
                 * isDelete : 0
                 * createDate : 1491837467000
                 * lastUpdateDate : 1491837467000
                 * originalFileInfoId : 105399
                 * thumbnatlFileInfoId : 105400
                 * originalFileInfo : {"objectId":105399,"fileName":"营业执照.jpg","extName":"jpg","serverId":"192.168.1.15:21","relativePath":"20173/1/16/edf8f6bb-ba23-4e1c-903d-2bdbf6eaf62e.jpg","createDate":1491753600000,"lastUpdateDate":1491753600000,"isDelete":0,"storeName":"edf8f6bb-ba23-4e1c-903d-2bdbf6eaf62e.jpg"}
                 * thumbnailFileInfo : {"objectId":105400,"fileName":"营业执照.jpg","extName":"jpg","serverId":"192.168.1.15:21","relativePath":"20173/1/16/43874b45-a41b-4397-b4dc-426758dc6be1.jpg","createDate":1491753600000,"lastUpdateDate":1491753600000,"isDelete":0,"storeName":"43874b45-a41b-4397-b4dc-426758dc6be1.jpg"}
                 */

                private int objectId;
                private int enterpriseId;
                private String name;
                private int type;
                private int isDelete;
                private long createDate;
                private long lastUpdateDate;
                private int originalFileInfoId;
                private int thumbnatlFileInfoId;
                private OriginalFileInfoBean originalFileInfo;
                private ThumbnailFileInfoBean thumbnailFileInfo;

                public int getObjectId() {
                    return objectId;
                }

                public void setObjectId(int objectId) {
                    this.objectId = objectId;
                }

                public int getEnterpriseId() {
                    return enterpriseId;
                }

                public void setEnterpriseId(int enterpriseId) {
                    this.enterpriseId = enterpriseId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getIsDelete() {
                    return isDelete;
                }

                public void setIsDelete(int isDelete) {
                    this.isDelete = isDelete;
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

                public int getOriginalFileInfoId() {
                    return originalFileInfoId;
                }

                public void setOriginalFileInfoId(int originalFileInfoId) {
                    this.originalFileInfoId = originalFileInfoId;
                }

                public int getThumbnatlFileInfoId() {
                    return thumbnatlFileInfoId;
                }

                public void setThumbnatlFileInfoId(int thumbnatlFileInfoId) {
                    this.thumbnatlFileInfoId = thumbnatlFileInfoId;
                }

                public OriginalFileInfoBean getOriginalFileInfo() {
                    return originalFileInfo;
                }

                public void setOriginalFileInfo(OriginalFileInfoBean originalFileInfo) {
                    this.originalFileInfo = originalFileInfo;
                }

                public ThumbnailFileInfoBean getThumbnailFileInfo() {
                    return thumbnailFileInfo;
                }

                public void setThumbnailFileInfo(ThumbnailFileInfoBean thumbnailFileInfo) {
                    this.thumbnailFileInfo = thumbnailFileInfo;
                }

                public static class OriginalFileInfoBean {
                    /**
                     * objectId : 105399
                     * fileName : 营业执照.jpg
                     * extName : jpg
                     * serverId : 192.168.1.15:21
                     * relativePath : 20173/1/16/edf8f6bb-ba23-4e1c-903d-2bdbf6eaf62e.jpg
                     * createDate : 1491753600000
                     * lastUpdateDate : 1491753600000
                     * isDelete : 0
                     * storeName : edf8f6bb-ba23-4e1c-903d-2bdbf6eaf62e.jpg
                     */

                    private int objectId;
                    private String fileName;
                    private String extName;
                    private String serverId;
                    private String relativePath;
                    private long createDate;
                    private long lastUpdateDate;
                    private int isDelete;
                    private String storeName;

                    public int getObjectId() {
                        return objectId;
                    }

                    public void setObjectId(int objectId) {
                        this.objectId = objectId;
                    }

                    public String getFileName() {
                        return fileName;
                    }

                    public void setFileName(String fileName) {
                        this.fileName = fileName;
                    }

                    public String getExtName() {
                        return extName;
                    }

                    public void setExtName(String extName) {
                        this.extName = extName;
                    }

                    public String getServerId() {
                        return serverId;
                    }

                    public void setServerId(String serverId) {
                        this.serverId = serverId;
                    }

                    public String getRelativePath() {
                        return relativePath;
                    }

                    public void setRelativePath(String relativePath) {
                        this.relativePath = relativePath;
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

                    public String getStoreName() {
                        return storeName;
                    }

                    public void setStoreName(String storeName) {
                        this.storeName = storeName;
                    }
                }

                public static class ThumbnailFileInfoBean {
                    /**
                     * objectId : 105400
                     * fileName : 营业执照.jpg
                     * extName : jpg
                     * serverId : 192.168.1.15:21
                     * relativePath : 20173/1/16/43874b45-a41b-4397-b4dc-426758dc6be1.jpg
                     * createDate : 1491753600000
                     * lastUpdateDate : 1491753600000
                     * isDelete : 0
                     * storeName : 43874b45-a41b-4397-b4dc-426758dc6be1.jpg
                     */

                    private int objectId;
                    private String fileName;
                    private String extName;
                    private String serverId;
                    private String relativePath;
                    private long createDate;
                    private long lastUpdateDate;
                    private int isDelete;
                    private String storeName;

                    public int getObjectId() {
                        return objectId;
                    }

                    public void setObjectId(int objectId) {
                        this.objectId = objectId;
                    }

                    public String getFileName() {
                        return fileName;
                    }

                    public void setFileName(String fileName) {
                        this.fileName = fileName;
                    }

                    public String getExtName() {
                        return extName;
                    }

                    public void setExtName(String extName) {
                        this.extName = extName;
                    }

                    public String getServerId() {
                        return serverId;
                    }

                    public void setServerId(String serverId) {
                        this.serverId = serverId;
                    }

                    public String getRelativePath() {
                        return relativePath;
                    }

                    public void setRelativePath(String relativePath) {
                        this.relativePath = relativePath;
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

                    public String getStoreName() {
                        return storeName;
                    }

                    public void setStoreName(String storeName) {
                        this.storeName = storeName;
                    }
                }
            }
        }
    }
}
