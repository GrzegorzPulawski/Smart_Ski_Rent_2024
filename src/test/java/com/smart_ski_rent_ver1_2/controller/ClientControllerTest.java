package com.smart_ski_rent_ver1_2.controller;

import com.smart_ski_rent_ver1_2.request.CreateClientRequest;
import com.smart_ski_rent_ver1_2.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@WebMvcTest(ClientController.class)
public class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;
    private final ObjectMapper objectMapper= new ObjectMapper();

    @Test
    public void givenClient_whenAdd_ThenReturnStatus() throws Exception {

        // given
       CreateClientRequest createClientRequest = new CreateClientRequest("Marcin","Nowak", "ASD123456", 123444555);

          doNothing().when(clientService).addClient(any(CreateClientRequest.class));

        // when
         // Wysłanie żądania POST do endpointu kontrolera
        mockMvc.perform(post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(createClientRequest)))
                        .andExpect(status().isOk());
        // then
        // Weryfikacja, czy metoda addClient została wywołana
        verify(clientService).addClient(any(CreateClientRequest.class));
    }
}