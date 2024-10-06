package com.smart_ski_rent_ver1_2.company.DTO;

public class CompanyDTO {
    private Long idCompany;
    private String companyName;
    private String companyNIP;
    private String nameUser;

    public CompanyDTO(Long idCompany, String companyName, String companyNIP, String nameUser) {
        this.idCompany = idCompany;
        this.companyName = companyName;
        this.companyNIP = companyNIP;
        this.nameUser = nameUser;
    }

    public Long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Long idCompany) {
        this.idCompany = idCompany;
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

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
}
