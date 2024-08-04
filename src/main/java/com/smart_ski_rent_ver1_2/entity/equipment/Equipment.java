package com.smart_ski_rent_ver1_2.entity.equipment;

import com.smart_ski_rent_ver1_2.entity.renting.Renting;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipments")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private Long idEquipment;

    @Column(name ="name_Equipment")
    private String nameEquipment;

    @Column(name = "price_Equipment")
    private Double priceEquipment;


    @OneToMany(mappedBy = "equipment")
    private List<Renting> renting;

}
