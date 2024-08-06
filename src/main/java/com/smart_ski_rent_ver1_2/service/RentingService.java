package com.smart_ski_rent_ver1_2.service;

import com.smart_ski_rent_ver1_2.entity.client.Client;
import com.smart_ski_rent_ver1_2.entity.equipment.Equipment;
import com.smart_ski_rent_ver1_2.entity.renting.Renting;
import com.smart_ski_rent_ver1_2.exception.EquipmentAlreadyRentedException;
import com.smart_ski_rent_ver1_2.repositories.ClientRepository;
import com.smart_ski_rent_ver1_2.repositories.EquipmentRepository;
import com.smart_ski_rent_ver1_2.repositories.RentingRepository;
import com.smart_ski_rent_ver1_2.request.CreateRentingRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.ceil;

@Service
@RequiredArgsConstructor
public class RentingService {
    private final RentingRepository rentingRepository;
    private final ClientRepository clientRepository;
    private final EquipmentRepository equipmentRepository;

    public void createRenting(CreateRentingRequest createRentingRequest) {
        Optional<Client> optionalClient = clientRepository.findById(createRentingRequest.getIdClient());
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();

            Optional<Equipment> optionalEquipment = equipmentRepository.findById(createRentingRequest.getIdEquipment());
            if (optionalEquipment.isPresent()) {
                Equipment equipment = optionalEquipment.get();

                //Sprawdzam czy sprzet nie jest wypożyczony? czy o taką logikę businesową nam chodziło?
                if (!isRented(equipment)) {
                    //Kreujemy wypozyczenie
                    Renting renting = Renting.builder()
                            .client(client)
                            .equipment(equipment)
                            .dateRenting(LocalDateTime.now())
                            //sprawdzić czy pobrac cene z renting czy z equipment
                            .rentingPrice(equipment.getPriceEquipment())
                            .build();
                    rentingRepository.save(renting);

                } else {
                    throw new EquipmentAlreadyRentedException("Equipment is already  rented");
                }
            }
        }
    }
    private boolean isRented(Equipment equipment){
        for (Renting renting : equipment.getRenting()) {
            if (renting.getDateRenting() != null) {
                return true;
            }
        }
        return false;
    }
    public void returnRenting(Long idRenting) {
        Optional<Renting> optionalRenting = rentingRepository.findById(idRenting);
        if (optionalRenting.isPresent()) {
            Renting renting = optionalRenting.get();

            if (renting.getDateOfReturn() == null) {
                renting.setDateRenting(LocalDateTime.now());
                Double rentingPrice = renting.getRentingPrice();
                //cena ostateczna za wypozyczenie
                Double priceOfDuration = rentingPrice * getTimeDuration(renting);
                rentingRepository.save(renting);
            }
        }throw new EntityNotFoundException("Unable to find renting with id: "+ idRenting);
    }
    //Ustalenie czasu wypozyczenia
    public Long getTimeDuration(Renting renting){
        LocalDateTime dateRenting = renting.getDateRenting();
        LocalDateTime dateOfReturn = (renting.getDateOfReturn() != null )? renting.getDateOfReturn() : LocalDateTime.now();
        Duration duration = Duration.between(dateOfReturn, dateRenting);
        double days = duration.toHours() / 24.0;
        return (long) Math.ceil(days);
    }
    public List<Renting> listRentings(){
        return rentingRepository.findAll();
    }
}
