package com.smart_ski_rent_ver1_2.security.repositories;

import com.smart_ski_rent_ver1_2.security.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByAppUserName(String AppUserName);
}
