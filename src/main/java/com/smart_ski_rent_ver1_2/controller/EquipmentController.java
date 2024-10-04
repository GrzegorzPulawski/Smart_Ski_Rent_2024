package com.smart_ski_rent_ver1_2.controller;

import com.smart_ski_rent_ver1_2.dto.EquipmentDTO;
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
    @PostMapping("/add")
    public void createEquipment(@RequestBody CreateEquipmentRequest createEquipmentRequest){
        log.info("Create equipment" + createEquipmentRequest);
        equipmentService.createEquipment(createEquipmentRequest);
    }
    @GetMapping
    public List<EquipmentDTO> equipmentsList(){
        List<EquipmentDTO> equipmentList = equipmentService.listEquipments();
        log.info("List of equipments has: "+  equipmentList.size() +" positions");
        return equipmentService.listEquipments();
    }
    @DeleteMapping("/delete")
    public void deleteEquipment(@RequestParam Long idEquipment){
        log.info("Equipment is deleted with id: " + idEquipment );
        equipmentService.deleteEquipment(idEquipment);
    }
}
