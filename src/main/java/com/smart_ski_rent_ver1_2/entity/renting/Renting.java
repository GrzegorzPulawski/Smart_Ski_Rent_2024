package com.smart_ski_rent_ver1_2.entity.renting;


import com.smart_ski_rent_ver1_2.entity.client.Client;
import com.smart_ski_rent_ver1_2.entity.equipment.Equipment;
import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "rentings")
public class Renting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateRenting() {
        return dateRenting;
    }

    public void setDateRenting(LocalDateTime dateRenting) {
        this.dateRenting = dateRenting;
    }

    public LocalDateTime getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(LocalDateTime dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public String getNameEquipment() {
        return nameEquipment;
    }

    public void setNameEquipment(String nameEquipment) {
        this.nameEquipment = nameEquipment;
    }

    public Double getRentingPrice() {
        return rentingPrice;
    }

    public void setRentingPrice(Double rentingPrice) {
        this.rentingPrice = rentingPrice;
    }

    public Double getPriceOfDuration() {
        return priceOfDuration;
    }

    public void setPriceOfDuration(Double priceOfDuration) {
        this.priceOfDuration = priceOfDuration;
    }

    public Long getDaysOfRental() {
        return daysOfRental;
    }

    public void setDaysOfRental(Long daysOfRental) {
        this.daysOfRental = daysOfRental;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Renting renting = (Renting) o;
        return Objects.equals(id, renting.id) && Objects.equals(dateRenting, renting.dateRenting) && Objects.equals(dateOfReturn, renting.dateOfReturn) && Objects.equals(nameEquipment, renting.nameEquipment) && Objects.equals(rentingPrice, renting.rentingPrice) && Objects.equals(priceOfDuration, renting.priceOfDuration) && Objects.equals(daysOfRental, renting.daysOfRental) && Objects.equals(equipment, renting.equipment) && Objects.equals(client, renting.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateRenting, dateOfReturn, nameEquipment, rentingPrice, priceOfDuration, daysOfRental, equipment, client);
    }


}
