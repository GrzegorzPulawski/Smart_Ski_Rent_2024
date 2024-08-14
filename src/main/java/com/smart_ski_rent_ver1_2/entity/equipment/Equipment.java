package com.smart_ski_rent_ver1_2.entity.equipment;

import com.smart_ski_rent_ver1_2.dto.EquipmentDTO;
import com.smart_ski_rent_ver1_2.entity.renting.Renting;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "equipments")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private Long id;

    @Column(name ="name_Equipment")
    private String nameEquipment;

    @Column(name = "price_Equipment")
    private Double priceEquipment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEquipment() {
        return nameEquipment;
    }

    public void setNameEquipment(String nameEquipment) {
        this.nameEquipment = nameEquipment;
    }

    public Double getPriceEquipment() {
        return priceEquipment;
    }

    public void setPriceEquipment(Double priceEquipment) {
        this.priceEquipment = priceEquipment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return Objects.equals(id, equipment.id) && Objects.equals(nameEquipment, equipment.nameEquipment) && Objects.equals(priceEquipment, equipment.priceEquipment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameEquipment, priceEquipment);
    }
}
