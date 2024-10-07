package com.smart_ski_rent_ver1_2.service;

import com.smart_ski_rent_ver1_2.dto.ClientDTO;
import com.smart_ski_rent_ver1_2.entity.client.Client;
import com.smart_ski_rent_ver1_2.exception.ClientNotExistsException;
import com.smart_ski_rent_ver1_2.request.CreateClientRequest;
import com.smart_ski_rent_ver1_2.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    public void addClient(CreateClientRequest createClientRequest){
        boolean existsClient = clientRepository.existsByPhoneNumber(createClientRequest.getPhoneNumber());
        if (!existsClient){
            Client client = new Client(null, createClientRequest.getFirstName(), createClientRequest.getLastName(), createClientRequest.getIdentityCard(), createClientRequest.getPhoneNumber());
            clientRepository.save(client);
            return;
        }
        throw new EntityNotFoundException("Klient o podanym numerze telefonu już istnieje: "+ createClientRequest.getPhoneNumber());
    }
    public List<ClientDTO> findAllClients(){
        List<Client> clientList = clientRepository.findAllByOrderByIdClientDesc();
        List<ClientDTO> listAllClients = new ArrayList<>();
        for(Client client : clientList) {
            listAllClients.add(client.mapClientToDTO());
        }return listAllClients;
    }
    public List<ClientDTO> findByLastName(String lastName) {
        List<Client> clients =  clientRepository.findByLastName(lastName);
        return clients.stream()
                .map(client -> new ClientDTO(client.getIdClient(), client.getFirstName(), client.getLastName(), client.getIdentityCard(), client.getPhoneNumber()))
                .collect(Collectors.toList());
    }
    public void deleteClient(Long idClient ){
        boolean existsClient = clientRepository.existsById(idClient);
        if(existsClient) {
            clientRepository.deleteById(idClient);
        }else{
            throw  new ClientNotExistsException("Klient o "+idClient+" id nie istnieje. Wprowadż poprawne dane");
        }

    }

}
