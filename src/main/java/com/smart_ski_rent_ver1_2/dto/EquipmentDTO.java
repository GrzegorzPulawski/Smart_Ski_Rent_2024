package com.smart_ski_rent_ver1_2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDTO {
    private Long idEquipment;

    private String nameEquipment;
    private Double priceEquipment;
}
