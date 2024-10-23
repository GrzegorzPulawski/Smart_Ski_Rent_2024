package com.smart_ski_rent_ver1_2.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.smart_ski_rent_ver1_2.dto.ClientDTO;
import com.smart_ski_rent_ver1_2.entity.client.Client;
import com.smart_ski_rent_ver1_2.repositories.ClientRepository;
import com.smart_ski_rent_ver1_2.request.CreateClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private  ClientService clientService;
    private CreateClientRequest createClientRequest;

    @BeforeEach
    public void setUp(){
                createClientRequest = new CreateClientRequest(
                "Grzegorz",
                "Pulawski",
                "CDD123654777",
                502109888

        );
    }
    @Test
    public void givenClient_whenAddClient_thenClientSaved(){
        //given
        given(clientRepository.existsByPhoneNumber(502109888))
                .willReturn(false);

        //when
         clientService.addClient(createClientRequest);
         //then
        BDDMockito.then(clientRepository)
                .should()
                .save(ArgumentMatchers.any(Client.class));
    }
    @Test
    public void givenClients_whenFindAllClients_thenReturnClientDTOList() {
        // given
        Client client1 = new Client(1L, "John", "Doe", "ID12345", 123456789);
        Client client2 = new Client(2L, "Jane", "Doe", "ID67890", 987654321);
        List<Client> clientList = Arrays.asList(client1, client2);

        given(clientRepository.findAllByOrderByIdClientDesc()).willReturn(clientList);

        // when
        List<ClientDTO> clientDTOList = clientService.findAllClients();

        // then
        assertThat(clientDTOList).isNotNull();
        assertThat(clientDTOList.size()).isEqualTo(2);
        assertThat(clientDTOList.get(0).getFirstName()).isEqualTo("John");
        assertThat(clientDTOList.get(1).getFirstName()).isEqualTo("Jane");
    }
}
