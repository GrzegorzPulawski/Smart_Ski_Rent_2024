package com.smart_ski_rent_ver1_2.service;

import com.smart_ski_rent_ver1_2.dto.EquipmentDTO;
import com.smart_ski_rent_ver1_2.entity.equipment.Equipment;
import com.smart_ski_rent_ver1_2.exception.EquipmentNotExists;
import com.smart_ski_rent_ver1_2.repositories.EquipmentRepository;
import com.smart_ski_rent_ver1_2.request.CreateEquipmentRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class EquipmentServiceTest {
    @Mock
    private EquipmentRepository equipmentRepository;
    @InjectMocks
    private EquipmentService equipmentService;
    @Test
    public void givenEquipment_whenCreate_thenEquipmentSaved(){
        //given
        CreateEquipmentRequest createEquipmentRequest = new CreateEquipmentRequest(
              "Atomic",
                55.0
        );
        when(equipmentRepository.findByNameEquipment("Atomic")).thenReturn(Optional.empty());
        //when
        equipmentService.createEquipment(createEquipmentRequest);
        //then
       BDDMockito.then(equipmentRepository)
           .should()
              .save(ArgumentMatchers.any(Equipment.class));
    }
    @Test
    void givenExistingEquipmentId_whenDeleteEquipment_thenEquipmentDeleted() {
        // Given
        Long equipmentId = 1L;
        Equipment equipment = new Equipment();
        equipment.setIdEquipment(equipmentId);

        when(equipmentRepository.findById(equipmentId)).thenReturn(Optional.of(equipment));

        // When
        equipmentService.deleteEquipment(equipmentId);

        // Then
        verify(equipmentRepository, times(1)).deleteById(equipmentId);
    }
    @Test
    void givenNonExistingEquipmentId_whenDeleteEquipment_thenThrowException() {
        // Given
        Long equipmentId = 2L;

        when(equipmentRepository.findById(equipmentId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(EquipmentNotExists.class, () -> equipmentService.deleteEquipment(equipmentId));

        verify(equipmentRepository, never()).deleteById(anyLong());
    }
    @Test
    public void givenEquipments_whenFindAllEquipments_thenReturnEquipmentDTOList(){
        //given
        Equipment equipment1 = new Equipment(1L, "Atomic", 50.0);
        Equipment equipment2 = new Equipment(3L, "Rossignol", 40.0);
        List<Equipment> equipmentList = Arrays.asList(equipment1,equipment2);
        given(equipmentRepository.findAll()).willReturn(equipmentList);

        //when
        List<EquipmentDTO> equipmentDTOList = equipmentService.listEquipments();

        //then
        assertThat(equipmentDTOList).isNotNull();
        assertThat(equipmentDTOList.size()).isEqualTo(2);
        assertThat(equipmentDTOList.get(0).getNameEquipment()).isEqualTo("Atomic");
    }
}

