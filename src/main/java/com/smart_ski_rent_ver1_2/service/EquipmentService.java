package com.smart_ski_rent_ver1_2.service;

import com.smart_ski_rent_ver1_2.request.CreateEquipmentRequest;
import com.smart_ski_rent_ver1_2.entity.equipment.Equipment;
import com.smart_ski_rent_ver1_2.exception.EquipmentAlreadyExistsException;
import com.smart_ski_rent_ver1_2.exception.EquipmentNotExists;
import com.smart_ski_rent_ver1_2.repositories.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    public void createEquipment(CreateEquipmentRequest createEquipmentRequest){
        Optional<Equipment> optionalEquipment = equipmentRepository.findByNameEquipment(createEquipmentRequest.getNameEquipment());
        if (optionalEquipment.isEmpty()){
            Equipment equipment = new Equipment(null, createEquipmentRequest.getNameEquipment(),createEquipmentRequest.getPriceEquipment(),null);
         //   equipment.setNameEquipment(createEquipmentRequest.getNameEquipment());
            equipmentRepository.save(equipment);
        } else {
            throw new EquipmentAlreadyExistsException("Equipment with name: " + createEquipmentRequest.getNameEquipment()+ "already exists. Change name");
        }
    }
    public void deleteEquipment(Long idEquipment){
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(idEquipment);
        if(optionalEquipment.isPresent()) {
            equipmentRepository.deleteById(idEquipment);
        } else {
            throw new EquipmentNotExists("Equipment with id: " +idEquipment + "not exists");
        }
    }
    public List<Equipment> listEquipments(){
        return equipmentRepository.findAll();
    }

}
