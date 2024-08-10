package com.smart_ski_rent_ver1_2.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentingDTO {
    private Long idRenting;

    private String firstName;
    private String lastName;
    private Integer phoneNumber;
    private String identityCard;
    private LocalDateTime dateRenting;
    private LocalDateTime dateOfReturn;
    private String nameEquipment;
    private Double rentingPrice;
    private Double priceOfDuration;
    private Long daysOfRental;
}
