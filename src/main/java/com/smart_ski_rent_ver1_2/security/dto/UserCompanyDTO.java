package com.smart_ski_rent_ver1_2.security.dto;


public class UserCompanyDTO {
    private String username;
    private String companyName;
    private String companyNIP;

    public UserCompanyDTO(String username, String companyName, String companyNIP) {
        this.username = username;
        this.companyName = companyName;
        this.companyNIP = companyNIP;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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