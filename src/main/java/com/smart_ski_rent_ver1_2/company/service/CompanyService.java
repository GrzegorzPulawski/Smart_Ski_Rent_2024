package com.smart_ski_rent_ver1_2.company.service;

import com.smart_ski_rent_ver1_2.company.DTO.CompanyDTO;
import com.smart_ski_rent_ver1_2.company.entity.Company;
import com.smart_ski_rent_ver1_2.company.repository.CompanyRepository;
import com.smart_ski_rent_ver1_2.exception.NoCompanyForThisLoginException;
import com.smart_ski_rent_ver1_2.security.entity.AppUserEntity;
import com.smart_ski_rent_ver1_2.security.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private AppUserRepository appUserRepository;

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
    public Company getCompanyByLoginUser(String loginUser) {
        // Znajdź użytkownika na podstawie loginu
        Optional<AppUserEntity> userOptional = appUserRepository.findByUsername(loginUser);

        // Jeśli użytkownik istnieje
        if (userOptional.isPresent()) {
            AppUserEntity user = userOptional.get();
            // Wyszukaj firmę na podstawie nazwy użytkownika (nameUser)
            Optional<Company> companyOptional = companyRepository.findByNameUser(user.getUsername());

            // Jeśli firma istnieje i nazwa użytkownika się zgadza
            if (companyOptional.isPresent() && companyOptional.get().getNameUser().equals(loginUser)) {
                return companyOptional.get();
            }
        }
        // Rzucenie wyjątku w przypadku, gdy użytkownik lub firma nie pasują
        throw new NoCompanyForThisLoginException("Login nie pasuje do danych żadnej firmy");
    }

}






