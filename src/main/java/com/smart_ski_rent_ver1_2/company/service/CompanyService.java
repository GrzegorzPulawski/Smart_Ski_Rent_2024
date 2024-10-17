package com.smart_ski_rent_ver1_2.company.service;

import com.smart_ski_rent_ver1_2.company.DTO.CompanyDTO;
import com.smart_ski_rent_ver1_2.company.entity.Company;
import com.smart_ski_rent_ver1_2.exception.CompanyNotExistsException;
import com.smart_ski_rent_ver1_2.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;


    public CompanyDTO saveCompanyData(CompanyDTO companyDTO) {
        Optional<Company> existingCompanyName = companyRepository.findByCompanyName(companyDTO.getCompanyName());

        if (existingCompanyName.isPresent()) {
            throw new IllegalArgumentException("Firma " + companyDTO.getCompanyName() + " już istnieje w systemie.");
        }

        Company company = new Company();
        company.setCompanyName(companyDTO.getCompanyName());
        company.setCompanyNIP(companyDTO.getCompanyNIP());
        companyRepository.save(company);
        return companyDTO;
    }

    public List<CompanyDTO> finAllCompany() {
        List<Company> companyList = companyRepository.findAll();
        List<CompanyDTO> companyDTOList = new ArrayList<>();
        for (Company company : companyList) {
            companyDTOList.add(company.mapCompanyToDTO());
        }
        return companyDTOList;
    }

    public Company findCompanyByUser(String nameUserCompany) {
        Optional<Company> company = companyRepository.findCompanyByNameUserCompany(nameUserCompany);

        if (company.isPresent()) {
            return company.get(); // Jeśli firma istnieje, zwracamy ją
        } else {
            // Rzucamy wyjątek z własnym komunikatem, aby poinformować, że firma nie istnieje
            throw new CompanyNotExistsException("Firma dla użytkownika " + nameUserCompany + " nie została znaleziona.");
        }
    }


}





