package com.smart_ski_rent_ver1_2.security.dto;


public class UserLoginDTO {
    private String appUserName;
    private String password;
    private String companyName;
    private String companyNIP;

    public String getAppUserName() {
        return appUserName;
    }

    public void setAppUserName(String appUserName) {
        this.appUserName = appUserName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNIP() {
        return companyNIP;
    }

    public void setCompanyNIP(String companyNIP) {
        this.companyNIP = companyNIP;
    }
}
