package com.smart_ski_rent_ver1_2.entity.client;

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
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="client_id")
    private Long idClient;

    @Column(name ="first_name")
    private String firstName;
    @Column(name ="last_name")
    private String lastName;
    @Column(name ="identity_card")
    private String identityCard;
    @Column(name ="phone_number")
    private Integer phoneNumber;

    @OneToMany(mappedBy = "client")
    private List<Renting> rentingList;
}