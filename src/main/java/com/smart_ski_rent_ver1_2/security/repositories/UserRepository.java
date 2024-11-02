package com.smart_ski_rent_ver1_2.security.repositories;

import com.smart_ski_rent_ver1_2.security.jwtnew.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
