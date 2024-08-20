package com.smart_ski_rent_ver1_2.controller;

import com.smart_ski_rent_ver1_2.dto.RentingDTO;
import com.smart_ski_rent_ver1_2.entity.renting.Renting;
import com.smart_ski_rent_ver1_2.request.CreateRentingRequest;
import com.smart_ski_rent_ver1_2.service.RentingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public List<RentingDTO> listRentings(){
        List<RentingDTO> rentingList = rentingService.listRentings();
        log.info("List of rental has: " + rentingList.size() + " positions");
        return rentingService.listRentings();
    }
    @GetMapping("/show")
    public ResponseEntity<Renting> showRentingById(@RequestParam Long idRenting){
        log.info("Print renting with id: "+ idRenting);
        Optional<Renting> renting = rentingService.showRentingById(idRenting);

        if (renting.isPresent()) {
            return ResponseEntity.ok(renting.get()); // Zwraca 200 OK i obiekt Renting
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Zwraca 404 Not Found
        }
    }
    @GetMapping("/report")
    public Double getDailyRevenue(@RequestParam("date") String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        log.info("Print daily report");
        return rentingService.generateDailyRevenueReport(date);
    }
}
