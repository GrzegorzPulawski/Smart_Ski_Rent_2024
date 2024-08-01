package com.smart_ski_rent_ver1_2.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateClientRequest {


    private String firstName;
    private String lastName;
    private String identityCard;
    private Integer phoneNumber;

}
