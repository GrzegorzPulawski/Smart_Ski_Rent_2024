package com.smart_ski_rent_ver1_2.service;

import com.smart_ski_rent_ver1_2.dto.RentingDTO;
import com.smart_ski_rent_ver1_2.entity.client.Client;
import com.smart_ski_rent_ver1_2.entity.equipment.Equipment;
import com.smart_ski_rent_ver1_2.entity.renting.Renting;
import com.smart_ski_rent_ver1_2.exception.*;
import com.smart_ski_rent_ver1_2.repositories.ClientRepository;
import com.smart_ski_rent_ver1_2.repositories.EquipmentRepository;
import com.smart_ski_rent_ver1_2.repositories.RentingRepository;
import com.smart_ski_rent_ver1_2.request.CreateRentingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            // trzeba złapać klienta jesli jego id nie istnieje!!!
            Optional<Equipment> optionalEquipment = equipmentRepository.findById(createRentingRequest.getIdEquipment());
            if (optionalEquipment.isPresent()) {
                Equipment equipment = optionalEquipment.get();

                //Sprawdzam czy sprzet nie jest wypożyczony? czy o taką logikę businesową nam chodziło?
            //    if (isNotRented(equipment)) {
                    //Kreujemy wypozyczenie
                    Renting renting = Renting.builder()
                            .client(client)
                            .equipment(equipment)
                            .firstName(client.getFirstName())
                            .lastName(client.getLastName())
                            .phoneNumber(client.getPhoneNumber())
                            .identityCard(client.getIdentityCard())
                            .dateRenting(LocalDateTime.now())
                            .nameEquipment(equipment.getNameEquipment())
                            .rentingPrice(equipment.getPriceEquipment())
                            .build();
                    rentingRepository.save(renting);
                    return;
             //   }throw new EquipmentAlreadyRentedException("Equipment is already  rented"+ createRentingRequest.getIdEquipment());
            }throw new EquipmentNotExists("Equipment with id: " + createRentingRequest.getIdEquipment()+ " does not exist");
        }throw new ClientNotExistsException("Client with ID " + createRentingRequest.getIdClient() + " does not exist.");
    }

    //metoda mi nie pasuje!!!
 //   private boolean isNotRented(Equipment equipment){
  //      for (Renting renting : equipment.getRenting()) {
   //         if (renting.getDateOfReturn() == null) {
   //             return false;
    //        }
    //    }
    //    return true;
  //  }
    public Renting returnRenting(Long idRenting, Renting updateRenting) {
        Optional<Renting> optionalRenting = rentingRepository.findById(idRenting);
        if (optionalRenting.isPresent()) {
            Renting renting = optionalRenting.get();

            if (renting.getDateOfReturn() == null) {
                //ustal czas zdania sprzetu
                renting.setDateOfReturn(LocalDateTime.now());
                //ustal ilość dni wypożyczenia (metoda gettimeDuration)
                renting.setDaysOfRental(getTimeDuration(renting));

                //wylicz cenę ostateczną za okres wypożyczenia
                Double calculatePriceOfDuration = renting.getRentingPrice() * getTimeDuration(renting);
                //zapisz cenę ostateczną dla obiektu
                renting.setPriceOfDuration(calculatePriceOfDuration);
                //zapisz wszystko w bazie
              return   rentingRepository.save(renting);
            }   throw new RentingAlreadyReturnException("Renting have rented");
        }        throw new RentingIsNotExistsException("Unable to find renting with id: "+ idRenting);
    }
    //Ustalenie czasu wypozyczenia
    public Long getTimeDuration(Renting renting){
        LocalDateTime dateRenting = renting.getDateRenting();
        LocalDateTime dateOfReturn = (renting.getDateOfReturn() != null )? renting.getDateOfReturn() : LocalDateTime.now();

        Duration duration = Duration.between(dateRenting, dateOfReturn);
        long seconds = duration.getSeconds();
        double hours = seconds/ 3600.0;
        double days = hours / 24.0;
        return (long) Math.ceil(days);
    }
    public List<RentingDTO> listRentings(){
        List<Renting> rentingList= rentingRepository.findAll();
        List<RentingDTO> listAllRenting = new ArrayList<>();
        for (Renting renting: rentingList){
            listAllRenting.add(renting.mapRentingToDTO());
        }
        return listAllRenting;
    }
  //  public List <RentingDTO> showRentingById(Long id){
    //    List<Renting> rentingList = rentingRepository.findAll();
      //  List<RentingDTO> showRenting = new ArrayList<>();
        //for (Renting renting : rentingList){
          //  if(renting.getIdRenting() == id){
            //   showRenting.add(renting.mapRentingToDTO());
           // }
       // }return showRenting;
   // }
    public Optional<Renting> showRentingById(Long id){
        return rentingRepository.findById(id);
    }
    public Double generateDailyRevenueReport(LocalDate date) {
        // Pobierz wszystkie transakcje zakończone w danym dniu
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();

        List<Renting> rentalsForDay = rentingRepository.findByDateOfReturnBetween(startOfDay, endOfDay);

        // Sumowanie przychodów
        return rentalsForDay.stream()
                .mapToDouble(Renting::getPriceOfDuration)
                .sum();
    }
}
