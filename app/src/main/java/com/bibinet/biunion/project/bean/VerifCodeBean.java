package com.bibinet.biunion.project.bean;

import java.io.Serializable;

/**
 * Created by bibinet on 2017-6-16.
 */

public class VerifCodeBean implements Serializable {

    /**
     * resCode : 0000
     * modelAndView : {"model":{"resCode":"0000","resMessage":"操作成功"},"empty":false,"reference":false,"modelMap":{"resCode":"0000","resMessage":"操作成功"}}
     * resMessage : 操作成功
     */

    private String resCode;
    private ModelAndViewBean modelAndView;
    private String resMessage;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public ModelAndViewBean getModelAndView() {
        return modelAndView;
    }

    public void setModelAndView(ModelAndViewBean modelAndView) {
        this.modelAndView = modelAndView;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

    public static class ModelAndViewBean {
        /**
         * model : {"resCode":"0000","resMessage":"操作成功"}
         * empty : false
         * reference : false
         * modelMap : {"resCode":"0000","resMessage":"操作成功"}
         */

        private ModelBean model;
        private boolean empty;
        private boolean reference;
        private ModelMapBean modelMap;

        public ModelBean getModel() {
            return model;
        }

        public void setModel(ModelBean model) {
            this.model = model;
        }

        public boolean isEmpty() {
            return empty;
        }

        public void setEmpty(boolean empty) {
            this.empty = empty;
        }

        public boolean isReference() {
            return reference;
        }

        public void setReference(boolean reference) {
            this.reference = reference;
        }

        public ModelMapBean getModelMap() {
            return modelMap;
        }

        public void setModelMap(ModelMapBean modelMap) {
            this.modelMap = modelMap;
        }

        public static class ModelBean {
            /**
             * resCode : 0000
             * resMessage : 操作成功
             */

            private String resCode;
            private String resMessage;

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
        }

        public static class ModelMapBean {
            /**
             * resCode : 0000
             * resMessage : 操作成功
             */

            private String resCode;
            private String resMessage;

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
        }
    }
}
