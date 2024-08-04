package com.smart_ski_rent_ver1_2.service;

import com.smart_ski_rent_ver1_2.entity.client.Client;
import com.smart_ski_rent_ver1_2.request.CreateClientRequest;
import com.smart_ski_rent_ver1_2.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    public void addClient(CreateClientRequest createClientRequest){
        boolean existsClient = clientRepository.existsByPhoneNumber(createClientRequest.getPhoneNumber());
        if (!existsClient){
            Client client = new Client(null, createClientRequest.getFirstName(), createClientRequest.getLastName(), createClientRequest.getIdentityCard(), createClientRequest.getPhoneNumber(),null);
            clientRepository.save(client);
            return;
        }
        throw new EntityNotFoundException("Klient o podanym numerze telefonu już istnieje: "+ createClientRequest.getPhoneNumber());
    }
    public List<Client> findAllClients(){
        return clientRepository.findAll();

    }
}