package com.smart_ski_rent_ver1_2.repositories;

import com.smart_ski_rent_ver1_2.entity.equipment.Equipment;
import com.smart_ski_rent_ver1_2.repositories.EquipmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class EquipmentRepositoryTest {
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Test
    @DisplayName("Zapisywanie sprzętu")
    public void givenEquipment_whenSave_thenReturnSavedEquipment(){
        //given
        Equipment equipment1 = new Equipment(1L, "Atomic", 50.0);
        //when
        Equipment save = equipmentRepository.save(equipment1);
        //then
        assertThat(save).isNotNull();
        assertThat(save.getIdEquipment()).isGreaterThan(0);
        assertThat(save).isEqualTo(equipment1);
    }
    @Test
    public void givenEquipment_whenFindAll_thenReturnListOfEquipment(){
        //given
        Equipment equipment1 = new Equipment(1L, "Atomic", 50.0);
        Equipment equipment2 = new Equipment(3L, "Rossignol", 40.0);

        equipmentRepository.save(equipment1);
        equipmentRepository.save(equipment2);
        //when
        List<Equipment> equipmentList = equipmentRepository.findAll();
        //then
        assertThat(equipmentList.size()).isEqualTo(2);
        assertThat(equipmentList.get(0).getNameEquipment()).isEqualTo("Atomic");
        assertThat(equipmentList.get(1).getNameEquipment()).isEqualTo("Rossignol");
    }
}
