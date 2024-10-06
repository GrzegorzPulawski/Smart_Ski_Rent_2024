package com.smart_ski_rent_ver1_2.company.entity;

import jakarta.persistence.*;

@Entity
@Table(name="companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="company_id")
    private Long idCompany;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_nip")
    private String companyNIP;

    @Column (name ="name_user")
    private String nameUser;


    public Company(){};

    public Company(Long idCompany, String companyName, String companyNIP, String nameUser) {
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


