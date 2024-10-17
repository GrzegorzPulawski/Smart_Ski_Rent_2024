package com.smart_ski_rent_ver1_2.company.controller;

import com.smart_ski_rent_ver1_2.company.DTO.CompanyDTO;
import com.smart_ski_rent_ver1_2.company.entity.Company;
import com.smart_ski_rent_ver1_2.company.service.CompanyService;
import com.smart_ski_rent_ver1_2.exception.CompanyNotExistsException;
import com.smart_ski_rent_ver1_2.security.dto.UserCompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // Endpoint to save company data
    @PostMapping("/save")
    public ResponseEntity<CompanyDTO> saveCompanyData(@RequestBody CompanyDTO companyDTO) {
        try {
            // Call the service to save company data
            CompanyDTO savedCompany = companyService.saveCompanyData(companyDTO);
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
    public ResponseEntity<?> getCompanyByUser(@RequestParam String nameUser) {
        try {
            Company company = companyService.findCompanyByUser(nameUser);
            return ResponseEntity.ok(company);
        } catch (CompanyNotExistsException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}





