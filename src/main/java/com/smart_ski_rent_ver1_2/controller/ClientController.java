package com.smart_ski_rent_ver1_2.controller;

import com.smart_ski_rent_ver1_2.dto.ClientDTO;
import com.smart_ski_rent_ver1_2.entity.client.Client;
import com.smart_ski_rent_ver1_2.request.CreateClientRequest;
import com.smart_ski_rent_ver1_2.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Current user accessing /api/clients: " + authentication.getName());

        List<ClientDTO> clientList = clientService.findAllClients();
        log.info("List of clients has: " + clientList.size() + " positions");
        return clientService.findAllClients();
    }
    @GetMapping("/findByLastName")
    public ResponseEntity<List<ClientDTO>> getClientByLastName(@RequestParam("lastName") String lastName) {
        List<ClientDTO> clients = clientService.findByLastName(lastName);
        if (clients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clients);
        }
        return ResponseEntity.ok(clients);
    }
    @DeleteMapping("/delete")
    public void deleteClientById(@RequestParam("idClient") Long idClient){
        log.info("Delete client with ID: "+idClient);
        clientService.deleteClient(idClient);
    }

}
