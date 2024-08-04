package com.smart_ski_rent_ver1_2.controller;

import com.smart_ski_rent_ver1_2.entity.equipment.Equipment;
import com.smart_ski_rent_ver1_2.request.CreateEquipmentRequest;
import com.smart_ski_rent_ver1_2.service.EquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }
    @PostMapping
    public void createEquipment(@RequestBody CreateEquipmentRequest createEquipmentRequest){
        log.info("Create equipment" + createEquipmentRequest);
        equipmentService.createEquipment(createEquipmentRequest);
    }
    @GetMapping
    public List<Equipment> equipmentsList(){
        log.info("Equipments list below: ");
        return equipmentService.listEquipments();
    }
    @DeleteMapping
    public void deleteEquipment(@RequestParam Long idEquipment){
        log.info("Equipment is deleted with id: " + idEquipment );
        equipmentService.deleteEquipment(idEquipment);
    }
}
