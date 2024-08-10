package com.smart_ski_rent_ver1_2.entity.renting;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smart_ski_rent_ver1_2.dto.RentingDTO;
import com.smart_ski_rent_ver1_2.entity.client.Client;
import com.smart_ski_rent_ver1_2.entity.equipment.Equipment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rental")
public class Renting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "renting_id")
    private Long idRenting;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="phone_number")
    private Integer phoneNumber;
    @Column(name="identity_card")
    private String identityCard;
    @CreationTimestamp
    @Column(name ="date_renting")
    private LocalDateTime dateRenting;
    @Column (name = "date_return")
    private LocalDateTime dateOfReturn;

    @Column(name="name_equipment")
    private String nameEquipment;

    @Column(name = "price_renting")
    private Double rentingPrice;

    @Column(name = "price_of_duration")
    private Double priceOfDuration;

    @Column(name ="days_of_rental")
    private Long daysOfRental;

    @ManyToOne
    @JoinColumn(name = "equipments_equipment_id")
    private Equipment equipment;
    @ManyToOne
    @JoinColumn(name = "clients_client_id")
    private Client client;

    public RentingDTO mapRentingToDTO(){return new RentingDTO(idRenting, this.firstName, this.lastName, this.phoneNumber, this.identityCard, this.dateRenting, this.getDateOfReturn(),this.getNameEquipment(),this.getRentingPrice(),this.getPriceOfDuration(),this.daysOfRental);}
}
