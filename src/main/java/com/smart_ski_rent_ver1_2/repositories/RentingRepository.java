package com.smart_ski_rent_ver1_2.repositories;

import com.smart_ski_rent_ver1_2.equipment.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentingRepository extends JpaRepository<Equipment, Long> {
}