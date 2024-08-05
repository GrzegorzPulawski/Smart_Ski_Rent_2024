package com.smart_ski_rent_ver1_2.entity.renting;

import com.smart_ski_rent_ver1_2.entity.client.Client;
import com.smart_ski_rent_ver1_2.entity.equipment.Equipment;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Data
@Builder
@Entity
@Table(name = "rental")
public class Renting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "renting_id")
    private Long idRenting;

    @CreationTimestamp
    @Column(name ="date_renting")
    private LocalDateTime dateRenting;
    @Column (name = "date_return")
    private LocalDateTime dateOfReturn;

    @Column(name = "price_renting")
    private Double rentingPrice;

    @Column(name = "price_of_duration")
    private Double priceOfDuration;
    @ManyToOne
    private Equipment equipment;
    @ManyToOne
    private Client client;

}
