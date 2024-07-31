package com.smart_ski_rent_ver1_2.renting;

import com.smart_ski_rent_ver1_2.equipment.Equipment;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Renting {
    @Id
    private Long idRenting;
    @CreationTimestamp
    private LocalDateTime dateRenting;
    private LocalDateTime dateOfReturn;
    private Double rentingPrice;
    @ManyToOne
    private Equipment equipment;

}
