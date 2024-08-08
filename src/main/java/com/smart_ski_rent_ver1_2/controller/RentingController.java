package com.smart_ski_rent_ver1_2.controller;

import com.smart_ski_rent_ver1_2.entity.renting.Renting;
import com.smart_ski_rent_ver1_2.request.CreateRentingRequest;
import com.smart_ski_rent_ver1_2.service.RentingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/rentings")
public class RentingController {
    private final RentingService rentingService;

    public RentingController(RentingService rentingService) {
        this.rentingService = rentingService;
    }
    @PostMapping
    public void createRenting(@RequestBody CreateRentingRequest createRentingRequest){
        log.info("Created renting: "+ createRentingRequest);
        rentingService.createRenting(createRentingRequest);
    }

    @PutMapping("/return/{idRenting}")
    public ResponseEntity<Renting> returnRenting(
            @PathVariable("idRenting") Long idRenting,
            @RequestBody Renting updateRenting ) {
        log.info("Renting with id: " + idRenting + " return");

    Renting renting = rentingService.returnRenting(idRenting, updateRenting);
    return  ResponseEntity.ok(renting);
    }
    @GetMapping
    public List<Renting> listRentings(){
        List<Renting> rentingList = rentingService.listRentings();
        log.info("List of rental has: " + rentingList.size() + "positions");
        return rentingService.listRentings();
    }
    @GetMapping("/show")
    public List<Renting> showRentingById(@RequestParam Long idRenting){
        log.info("Print renting with id: "+ idRenting);
        return rentingService.showRentingById(idRenting);
    }
}
