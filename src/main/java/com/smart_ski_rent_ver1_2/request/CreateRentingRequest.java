package com.smart_ski_rent_ver1_2.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentingRequest {
    private Long idClient;
    private List<Long> idEquipment;
    private Long idcompany;

}
