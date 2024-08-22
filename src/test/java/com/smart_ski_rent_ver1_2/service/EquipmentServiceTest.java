package com.smart_ski_rent_ver1_2.service;

import com.smart_ski_rent_ver1_2.entity.equipment.Equipment;
import com.smart_ski_rent_ver1_2.repositories.EquipmentRepository;
import com.smart_ski_rent_ver1_2.request.CreateEquipmentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.any;

@ExtendWith(MockitoExtension.class)
public class EquipmentServiceTest {
    @Mock
    private EquipmentRepository equipmentRepository;
    @InjectMocks
    private EquipmentService equipmentService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenEquipment_whenCreate_thenEquipmentSaved(){
        //given
        CreateEquipmentRequest createEquipmentRequest = new CreateEquipmentRequest(
              "Atomic",
                55.0
        );
        Equipment equipment = new Equipment(
                1L,
                "dd",
                12.0
        );
       BDDMockito.when(equipmentRepository.save(ArgumentMatchers.any(Equipment.class))).thenReturn(equipment);

        //when
        equipmentService.createEquipment(createEquipmentRequest);
        //then
        BDDMockito.then(equipmentRepository)
                .should()
                .save(ArgumentMatchers.any(Equipment.class));
    }
}
