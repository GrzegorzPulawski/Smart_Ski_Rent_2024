package com.smart_ski_rent_ver1_2.security.controller;

import com.google.common.collect.Lists;
import com.smart_ski_rent_ver1_2.security.model.CompanyUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/company")
public class CompanyUserController {

    private final List<CompanyUser> COMPANYUSERS = Lists.newArrayList(
            new CompanyUser(1, "Mandragora")
    );
    @GetMapping("/{id}")
    public CompanyUser getCompany(@PathVariable Integer id) {
        return COMPANYUSERS.stream()
                .filter(companyUser -> id.equals(companyUser.getCompanyUserId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Company with id " + id + " not found"
                ));
    }

}