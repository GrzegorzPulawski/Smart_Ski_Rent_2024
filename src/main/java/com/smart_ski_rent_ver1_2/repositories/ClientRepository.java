package com.smart_ski_rent_ver1_2.repositories;

import com.smart_ski_rent_ver1_2.dto.ClientDTO;
import com.smart_ski_rent_ver1_2.entity.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByPhoneNumber(Integer phoneNumber);
    List<Client> findByLastName(String lastName);

}
