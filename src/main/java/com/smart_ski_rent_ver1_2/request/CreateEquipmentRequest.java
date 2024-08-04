package com.smart_ski_rent_ver1_2.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEquipmentRequest {

    private String nameEquipment;
    private Double priceEquipment;
}
