package com.bibinet.biunion.project.bean;

import java.io.Serializable;

/**
 * Created by bibinet on 2017-6-1.
 */

public class ProjectInfoBean implements Serializable {
    private String CompanyName;
    private String CompanyDescrp;
    private String Loaction;
    private String time;

    public ProjectInfoBean(String companyName, String companyDescrp, String loaction, String time) {
        CompanyName = companyName;
        CompanyDescrp = companyDescrp;
        Loaction = loaction;
        this.time = time;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyDescrp() {
        return CompanyDescrp;
    }

    public void setCompanyDescrp(String companyDescrp) {
        CompanyDescrp = companyDescrp;
    }

    public String getLoaction() {
        return Loaction;
    }

    public void setLoaction(String loaction) {
        Loaction = loaction;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
