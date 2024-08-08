package com.smart_ski_rent_ver1_2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentingDTO {
    private Long idRenting;

    private LocalDateTime dateRenting;
    private LocalDateTime dateOfReturn;
    private Double rentingPrice;
    private Double priceOfDuration;
    private Long daysOfRental;
}
