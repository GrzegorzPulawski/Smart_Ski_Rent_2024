package com.smart_ski_rent_ver1_2.security.dto;

public class LoginRequest {
    private String appUserName;
    private String password;

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
}
