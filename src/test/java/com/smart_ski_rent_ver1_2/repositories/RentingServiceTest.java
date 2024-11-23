package com.smart_ski_rent_ver1_2.repositories;

import com.smart_ski_rent_ver1_2.entity.client.Client;
import com.smart_ski_rent_ver1_2.entity.equipment.Equipment;
import com.smart_ski_rent_ver1_2.entity.renting.Renting;
import com.smart_ski_rent_ver1_2.request.CreateRentingRequest;
import com.smart_ski_rent_ver1_2.service.RentingService;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
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
        // Użyj Mockito do zainicjowania mocków
        MockitoAnnotations.openMocks(this);

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
                .daysOfRental(0L) // All will be calculated during the test
                .build();

        when(rentingRepository.findById(rentingId)).thenReturn(Optional.of(renting));

        // Mockowanie tokenu, jeśli służysz do jego pozyskania
        // UserDto user = new UserDto(...);
        String token = "some_valid_token"; // Simulacja tokenu, może być zbędne w testach

        // when
        Renting updatedRenting = rentingService.returnRenting(rentingId, renting, token);


        // then
        ArgumentCaptor<Renting> rentingCaptor = ArgumentCaptor.forClass(Renting.class);
        verify(rentingRepository, times(1)).save(rentingCaptor.capture());

        Renting capturedRenting = rentingCaptor.getValue();

        assertNotNull(capturedRenting);
        assertNotNull(capturedRenting.getDateOfReturn());

        // Calculate expected values
        long expectedDaysOfRental = 3L; // Ustal wartość dni wypożyczenia
        assertEquals(expectedDaysOfRental, capturedRenting.getDaysOfRental());
        assertEquals(40.0 * expectedDaysOfRental, capturedRenting.getPriceOfDuration());
    }

}


