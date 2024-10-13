package com.smart_ski_rent_ver1_2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long idClient;
    private String firstName;
    private String lastName;
    private String identityCard;
    private Integer phoneNumber;
}
