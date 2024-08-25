package com.smart_ski_rent_ver1_2.repositories;

import com.smart_ski_rent_ver1_2.entity.client.Client;
import com.smart_ski_rent_ver1_2.entity.equipment.Equipment;
import com.smart_ski_rent_ver1_2.entity.renting.Renting;
import com.smart_ski_rent_ver1_2.request.CreateRentingRequest;
import com.smart_ski_rent_ver1_2.service.RentingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class RentingServiceTest {
    @Mock
    private RentingRepository rentingRepository;
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private EquipmentRepository equipmentRepository;
    @InjectMocks
    private RentingService rentingService;
    private CreateRentingRequest createRentingRequest;
    @Test
    public void givenRenting_whenCreateRequest_thenRentingSaved(){
        //given
        createRentingRequest = new CreateRentingRequest();
                createRentingRequest.setIdClient(1L);
                createRentingRequest.setIdEquipment(1L);

        Client client = new Client();
        client.setIdClient(1L);

        Equipment equipment = new Equipment();
        equipment.setIdEquipment(1L);

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(equipmentRepository.findById(1L)).thenReturn(Optional.of(equipment));


        //when
        rentingService.createRenting(createRentingRequest);
        //then
        verify(rentingRepository, times(1)).save(ArgumentMatchers.any(Renting.class));
        BDDMockito.then(rentingRepository)
                .should()
                .save(ArgumentMatchers.any(Renting.class));
    }

}
