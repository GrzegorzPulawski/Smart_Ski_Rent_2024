package com.smart_ski_rent_ver1_2.repositories;

import com.smart_ski_rent_ver1_2.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyName(String companyName);
    Optional<Company> findCompanyByNameUserCompany(String userToCompany);
}
