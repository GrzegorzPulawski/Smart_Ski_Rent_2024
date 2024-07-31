package com.smart_ski_rent_ver1_2.equipment;

import com.smart_ski_rent_ver1_2.renting.Renting;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipment;

    private String nameEquipment;
    private Double priceEquipment;


    @OneToMany
    private List<Renting> renting;

}
