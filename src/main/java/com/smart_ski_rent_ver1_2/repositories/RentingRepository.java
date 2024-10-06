package com.smart_ski_rent_ver1_2.repositories;

import com.smart_ski_rent_ver1_2.entity.equipment.Equipment;
import com.smart_ski_rent_ver1_2.entity.renting.Renting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RentingRepository extends JpaRepository<Renting, Long> {
    List<Renting> findByDateOfReturnBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
    List<Renting> findAllByOrderByRentingDateDesc();
}
