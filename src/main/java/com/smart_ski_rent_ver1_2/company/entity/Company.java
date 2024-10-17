package com.smart_ski_rent_ver1_2.company.entity;

import com.smart_ski_rent_ver1_2.company.DTO.CompanyDTO;
import com.smart_ski_rent_ver1_2.security.entity.AppUser;
import jakarta.persistence.*;

@Entity
@Table(name="companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long idCompany;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_nip")
    private String companyNIP;

    @Column(name="user_company")
    private String nameUserCompany;




    public Company() {
    }

    public Company(Long idCompany, String companyName, String companyNIP, String nameUserCompany) {
        this.idCompany = idCompany;
        this.companyName = companyName;
        this.companyNIP = companyNIP;
        this.nameUserCompany = nameUserCompany;
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

    public String getNameUserCompany() {
        return nameUserCompany;
    }

    public void setNameUserCompany(String nameUserCompany) {
        this.nameUserCompany = nameUserCompany;
    }

    public void setCompanyNIP(String companyNIP) {
        this.companyNIP = companyNIP;
    }
    public CompanyDTO mapCompanyToDTO(){return  new CompanyDTO(idCompany,this.companyName, this.companyNIP, this.nameUserCompany);}
}

