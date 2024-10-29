package com.smart_ski_rent_ver1_2.company.controller;

import com.smart_ski_rent_ver1_2.company.DTO.CompanyDTO;
import com.smart_ski_rent_ver1_2.company.entity.Company;
import com.smart_ski_rent_ver1_2.company.service.CompanyService;
import com.smart_ski_rent_ver1_2.exception.NoCompanyForThisLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // Endpoint to save company data
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('devel:write')")
    public ResponseEntity<CompanyDTO> saveCompanyData(@RequestBody CompanyDTO companyDTO) {
        try {
            // Call the service to save company data
            CompanyDTO savedCompany = companyService.saveCompanyData(companyDTO);
            return ResponseEntity.ok(savedCompany);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/data/{loginUser}")

    public ResponseEntity<Company> getCompanyByLoginUser(@PathVariable String loginUser){
        try {
            Company company = companyService.getCompanyByLoginUser(loginUser);
            return ResponseEntity.ok(company);
        } catch (NoCompanyForThisLoginException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}




