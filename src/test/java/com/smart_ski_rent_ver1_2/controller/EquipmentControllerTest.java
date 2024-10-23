package com.smart_ski_rent_ver1_2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart_ski_rent_ver1_2.dto.EquipmentDTO;
import com.smart_ski_rent_ver1_2.request.CreateEquipmentRequest;
import com.smart_ski_rent_ver1_2.service.EquipmentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@WebMvcTest(EquipmentController.class)
public class EquipmentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EquipmentService equipmentService;
    @InjectMocks
    private EquipmentController equipmentController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void givenEquipment_whenCreate_ThenReturnStatus() throws Exception {
        //given
        CreateEquipmentRequest createEquipmentRequest = new CreateEquipmentRequest(
                "Atomic", 50.0);
        doNothing().when(equipmentService).createEquipment(any(CreateEquipmentRequest.class));
        //when
        mockMvc.perform(post("/api/equipments/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createEquipmentRequest)))
                .andExpect(status().isOk());
        //then
        verify(equipmentService).createEquipment(any(CreateEquipmentRequest.class));
     }
     @Test
    public void givenEquipment_whenEquipmentList_thenReturnJsonArray() throws Exception {
        //given
         EquipmentDTO equipment1 = new EquipmentDTO(
                 1L,
                 "Atomic",
                 50.0);
         EquipmentDTO equipment2 = new EquipmentDTO(
                 2L,
                 "Rossignol",
                 40.0);
         List<EquipmentDTO> allEquipment = Arrays.asList(equipment1,equipment2);
         //when
         when(equipmentService.listEquipments()).thenReturn(allEquipment);
         //then
         mockMvc.perform(get("/api/equipments")
                 .contentType(MediaType.APPLICATION_JSON))
                 .andDo(print())
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$", hasSize(2)));
     }
}