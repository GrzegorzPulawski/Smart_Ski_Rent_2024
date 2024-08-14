package com.smart_ski_rent_ver1_2.controller;


import com.smart_ski_rent_ver1_2.entity.renting.Renting;

import com.smart_ski_rent_ver1_2.service.RentingService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/rentings")
public class RentingController {

    private final RentingService rentingService;

    public RentingController(RentingService rentingService) {
        this.rentingService = rentingService;
    }

    @GetMapping
    public ResponseEntity<List<Renting>> getAllRentings() {
        return ResponseEntity.ok(rentingService.getAllRentings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Renting> getRentingById(@PathVariable Long id) {
        return rentingService.getRentingById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Renting> createRenting(@RequestBody Renting renting) {
        return ResponseEntity.ok(rentingService.saveRenting(renting));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Renting> updateRenting(@PathVariable Long id, @RequestBody Renting rentingDetails) {
        return rentingService.getRentingById(id)
                .map(renting -> {
                    renting.setDateRenting(rentingDetails.getDateRenting());
                    renting.setDateOfReturn(rentingDetails.getDateOfReturn());
                    renting.setNameEquipment(rentingDetails.getNameEquipment());
                    renting.setRentingPrice(rentingDetails.getRentingPrice());
                    renting.setPriceOfDuration(rentingDetails.getPriceOfDuration());
                    renting.setDaysOfRental(rentingDetails.getDaysOfRental());
                    renting.setEquipment(rentingDetails.getEquipment());
                    return ResponseEntity.ok(rentingService.saveRenting(renting));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRenting(@PathVariable Long id) {
        rentingService.deleteRenting(id);
        return ResponseEntity.ok().build();
    }
}