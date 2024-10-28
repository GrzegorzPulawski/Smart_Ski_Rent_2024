package com.smart_ski_rent_ver1_2.company.repository;

import com.smart_ski_rent_ver1_2.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByNameUser(String nameUser);
}
