package com.smart_ski_rent_ver1_2.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentingRequest {
    private Long idClient;
    private Long idEquipment;
    //Czy jest potrzebne? Może wystarczy cena equipment?
    private Double rentingPrice;
}
