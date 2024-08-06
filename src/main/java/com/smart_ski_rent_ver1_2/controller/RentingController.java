package com.smart_ski_rent_ver1_2.controller;

import com.smart_ski_rent_ver1_2.entity.renting.Renting;
import com.smart_ski_rent_ver1_2.request.CreateRentingRequest;
import com.smart_ski_rent_ver1_2.service.RentingService;
import lombok.extern.slf4j.Slf4j;
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

    @PutMapping
    public void returnRenting(@RequestParam Long idRenting ){
        log.info("Renting with id: " + idRenting+" return");
        rentingService.returnRenting(idRenting);
    }
    @GetMapping
    public List<Renting> listRentings(){
        List<Renting> rentingList = rentingService.listRentings();
        log.info("List of rental has: " + rentingList.size() + "positions");
        return rentingService.listRentings();
    }
}
