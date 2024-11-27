package com.smart_ski_rent_ver1_2.controller;

import com.smart_ski_rent_ver1_2.dto.RentingDTO;
import com.smart_ski_rent_ver1_2.entity.renting.Renting;
import com.smart_ski_rent_ver1_2.request.CreateRentingRequest;
import com.smart_ski_rent_ver1_2.security.service.UserAuthProvider;
import com.smart_ski_rent_ver1_2.service.RentingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/rentings")
public class RentingController {
    private final RentingService rentingService;
    @Autowired
    private UserAuthProvider userAuthProvider;

    public RentingController(RentingService rentingService) {
        this.rentingService = rentingService;
    }

    @PostMapping
    public ResponseEntity<Void> createRenting(@RequestBody CreateRentingRequest createRentingRequest) {
        log.info("Created renting: " + createRentingRequest);
        rentingService.createRenting(createRentingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/return/{idRenting}")
    public ResponseEntity<Renting> returnRenting(
            @PathVariable("idRenting") Long idRenting,
            @RequestBody Renting updateRenting,
            @RequestHeader("Authorization") String authHeader) { // Odczytanie tokenu z nagłówka

        // Usuń prefiks "Bearer " z nagłówka, aby uzyskać token
        String token = authHeader.replace("Bearer ", "");

        try {
            // Wywołanie metody serwisowej z tokenem
            Renting renting = rentingService.returnRenting(idRenting, updateRenting, token);
            return ResponseEntity.ok(renting);
        } catch (RuntimeException e) {
            // Możesz dostosować obsługę błędów zgodnie z potrzebami
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping
    public List<RentingDTO> listRentings() {
        List<RentingDTO> rentingList = rentingService.listRentings();
        log.info("List of rental has: " + rentingList.size() + " positions");
        return rentingService.listRentings();
    }

    @GetMapping("/show")
    public ResponseEntity<RentingDTO> showRentingById(@RequestParam Long idRenting) {
        log.info("Print renting with id: " + idRenting);
        Optional<RentingDTO> rentingDTO = rentingService.showRentingById(idRenting);

        return rentingDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/report")
    public Double getDailyRevenue(@RequestParam("date") String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        log.info("Print daily report");
        return rentingService.generateDailyRevenueReport(date);
    }

    @PostMapping("/print")
    public ResponseEntity<List<RentingDTO>> printRentings(@RequestBody Map<String, List<Long>> requestBody) {
        List<Long> idRentings = requestBody.get("idRentings");

        if (idRentings == null || idRentings.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList()); // Return an empty list for consistency
        }
        try {
            List<RentingDTO> rentingsDTO = rentingService.findRentingsByIds(idRentings);
            return ResponseEntity.ok(rentingsDTO);
        } catch (Exception e) {
            // Log exception and return a server error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @GetMapping("/recentlyReturned") // Nowy endpoint
    public ResponseEntity<List<RentingDTO>> getRecentlyReturnedRentings() {
        try {
            List<RentingDTO> rentings = rentingService.findRecentlyReturnedRentings();
            return ResponseEntity.ok(rentings);
        } catch (Exception e) {
            log.error("Error fetching recently returned rentings:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}