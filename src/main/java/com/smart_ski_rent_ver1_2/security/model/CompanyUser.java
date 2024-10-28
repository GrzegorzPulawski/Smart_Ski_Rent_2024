package com.smart_ski_rent_ver1_2.security.model;

public class CompanyUser {
    private final Integer companyUserId;
    private final String companyUserName;

    public CompanyUser(Integer companyUserId, String companyUserName) {
        this.companyUserId = companyUserId;
        this.companyUserName = companyUserName;
    }

    public Integer getCompanyUserId() {
        return companyUserId;
    }

    public String getCompanyUserName() {
        return companyUserName;
    }
}
