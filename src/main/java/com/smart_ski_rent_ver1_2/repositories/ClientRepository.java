package com.smart_ski_rent_ver1_2.repositories;

import com.smart_ski_rent_ver1_2.exception.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByPhoneNumber(Integer phoneNumber);

}
