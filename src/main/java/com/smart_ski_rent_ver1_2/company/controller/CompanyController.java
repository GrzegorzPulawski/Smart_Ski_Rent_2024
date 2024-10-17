package com.smart_ski_rent_ver1_2.company.controller;

import com.smart_ski_rent_ver1_2.company.DTO.CompanyDTO;
import com.smart_ski_rent_ver1_2.company.entity.Company;
import com.smart_ski_rent_ver1_2.company.service.CompanyService;
import com.smart_ski_rent_ver1_2.exception.CompanyNotExistsException;
import com.smart_ski_rent_ver1_2.security.dto.UserCompanyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/save")
    public ResponseEntity<Company> saveCompanyData(@RequestBody Company company) {
        try {
            Company savedCompany = companyService.saveCompanyData(company);
            log.info("Created company with name"+ company.getCompanyName());
            return ResponseEntity.ok(savedCompany);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping
    public List<CompanyDTO> findAllCompany(){
            return companyService.finAllCompany();
        }

    @GetMapping("/findByUser")
    public ResponseEntity<?> getCompanyByUser(@RequestParam String nameUserCompany) {
        try {
            Company company = companyService.findCompanyByUser(nameUserCompany);
            return ResponseEntity.ok(company);
        } catch (CompanyNotExistsException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}





