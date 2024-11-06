package com.smart_ski_rent_ver1_2.company.service;

import com.smart_ski_rent_ver1_2.company.DTO.CompanyDTO;
import com.smart_ski_rent_ver1_2.company.entity.Company;
import com.smart_ski_rent_ver1_2.company.repository.CompanyRepository;
import com.smart_ski_rent_ver1_2.exception.NoCompanyForThisLoginException;
import com.smart_ski_rent_ver1_2.security.entity.User;
import com.smart_ski_rent_ver1_2.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;


    public CompanyDTO saveCompanyData(CompanyDTO companyDTO) {
        Optional<Company> existingNameUser = companyRepository
                .findByNameUser(companyDTO.getNameUser());
        if(existingNameUser.isPresent()){
            throw new IllegalArgumentException("Użytkownik " + companyDTO.getNameUser() + " już istnieje w systemie.");
        }

        Company company = new Company();
        company.setCompanyName(companyDTO.getCompanyName());
        company.setCompanyNIP(companyDTO.getCompanyNIP());
        company.setNameUser(companyDTO.getNameUser());

        companyRepository.save(company);
        return companyDTO;
    }

}






