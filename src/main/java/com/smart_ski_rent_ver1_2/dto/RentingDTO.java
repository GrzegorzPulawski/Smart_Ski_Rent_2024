package com.smart_ski_rent_ver1_2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentingDTO {
    private Long idRenting;
    private String firstName;
    private String lastName;
    private String identityCard;
    private Integer phoneNumber;

    private String dateRenting;
    private String nameEquipment;
    private Double priceEquipment;

    private String dateOfReturn;
    private Double priceOfDuration;
    private Long daysOfRental;

}
