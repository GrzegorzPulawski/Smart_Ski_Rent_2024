package com.smart_ski_rent_ver1_2.controller;

import com.smart_ski_rent_ver1_2.dto.ClientDTO;
import com.smart_ski_rent_ver1_2.request.CreateClientRequest;
import com.smart_ski_rent_ver1_2.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/clients")

public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @PostMapping
    public void createClient(@RequestBody CreateClientRequest createClientRequest){
        log.info("Create client:"+createClientRequest);
        clientService.addClient(createClientRequest);
    }
    @GetMapping
    public List<ClientDTO> clientsList(){
        List<ClientDTO> clientList = clientService.findAllClients();
        log.info("List of clients has: " + clientList.size() + " positions");
        return clientService.findAllClients();
    }
}
