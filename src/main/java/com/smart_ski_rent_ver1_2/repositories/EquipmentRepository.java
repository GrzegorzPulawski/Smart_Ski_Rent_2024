package com.smart_ski_rent_ver1_2.repositories;

import com.smart_ski_rent_ver1_2.entity.equipment.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
   Optional<Equipment> findByNameEquipment(String nameEquipment);
}
