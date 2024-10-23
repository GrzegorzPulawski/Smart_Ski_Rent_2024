package com.smart_ski_rent_ver1_2.repositories;

import com.smart_ski_rent_ver1_2.entity.client.Client;
import com.smart_ski_rent_ver1_2.entity.equipment.Equipment;
import com.smart_ski_rent_ver1_2.entity.renting.Renting;
import com.smart_ski_rent_ver1_2.request.CreateRentingRequest;
import com.smart_ski_rent_ver1_2.service.RentingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
                createRentingRequest.setIdEquipment(Collections.singletonList(1L));

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

    @Test
    void givenValidId_whenReturnRenting_thenRentingIsUpdated() {
        // given
        Long rentingId = 1L;

        Equipment equipment = new Equipment();
        equipment.setPriceEquipment(40.0);

        Renting renting = Renting.builder()
                .idRenting(rentingId)
                .equipment(equipment)
                .dateRenting(LocalDateTime.now().minusDays(3))
                .dateOfReturn(null)  // oznacza, że sprzęt nie został jeszcze zwrócony
                .priceOfDuration(0.0)
                .daysOfRental(3L)
                .build();

        when(rentingRepository.findById(rentingId)).thenReturn(Optional.of(renting));

        // when
        Renting updatedRenting = rentingService.returnRenting(rentingId, renting);


        // then
        ArgumentCaptor<Renting> rentingCaptor = ArgumentCaptor.forClass(Renting.class);
        verify(rentingRepository, times(1)).save(rentingCaptor.capture());

        Renting capturedRenting = rentingCaptor.getValue();

        assertNotNull(capturedRenting);
        assertNotNull(capturedRenting.getDateOfReturn());
        assertTrue(capturedRenting.getDaysOfRental() > 0);
        assertEquals(40.0 * capturedRenting.getDaysOfRental(), capturedRenting.getPriceOfDuration());
    }
}


