package com.smart_ski_rent_ver1_2.service;


import com.smart_ski_rent_ver1_2.entity.renting.Renting;
import com.smart_ski_rent_ver1_2.repositories.RentingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RentingService {

    private final RentingRepository rentingRepository;

    public RentingService(RentingRepository rentingRepository) {
        this.rentingRepository = rentingRepository;
    }

    public List<Renting> getAllRentings() {
        return rentingRepository.findAll();
    }

    public Optional<Renting> getRentingById(Long id) {
        return rentingRepository.findById(id);
    }

    public Renting saveRenting(Renting renting) {
        return rentingRepository.save(renting);
    }

    public void deleteRenting(Long id) {
        rentingRepository.deleteById(id);
    }
}
