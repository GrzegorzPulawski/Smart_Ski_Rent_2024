package com.smart_ski_rent_ver1_2.security.controller;

import com.google.common.collect.Lists;
import com.smart_ski_rent_ver1_2.security.model.CompanyUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/managment")
public class ManagmentController {

    private List<CompanyUser> company = Lists.newArrayList(
            new CompanyUser(1, "Mandragora")

    );
    public  List<CompanyUser> getallCompanyUsers(){return  company;}

    @PostMapping
    @PreAuthorize("hasAuthority('devel:write')")
    public String registerNewCompany(@RequestBody CompanyUser companyUser) {
        company.add(companyUser);
        return "registerNewCompany: " + companyUser;
    }
}
