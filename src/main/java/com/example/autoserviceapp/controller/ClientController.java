package com.example.autoserviceapp.controller;

import com.example.autoserviceapp.dto.mapper.ClientMapper;
import com.example.autoserviceapp.dto.request.ClientRequestDto;
import com.example.autoserviceapp.dto.response.ClientResponseDto;
import com.example.autoserviceapp.model.Client;
import com.example.autoserviceapp.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientMapper clientMapper;
    private final ClientService clientService;

    public ClientController(ClientMapper clientMapper, ClientService clientService) {
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }

    @PostMapping("/new")
    public Client saveClient(@RequestBody ClientRequestDto clientRequestDto) {
        Client client = clientMapper.mapToModel(clientRequestDto);
        return clientService.save(client);
    }

    @PutMapping("add-order")
    public ClientResponseDto addOrderToClient(@RequestParam Long clientId,
                                              @RequestParam Long orderId) {
        Client client = clientService.addOrderToClient(clientId, orderId);
        return clientMapper.mapToDto(client);
    }

    //Test
    @GetMapping("/{id}")
    public ClientResponseDto getClient(@PathVariable Long id) {
        Client client = clientService.findById(id);
        ClientResponseDto dto = clientMapper.mapToDto(client);
        System.out.println(client.getOrders());
        return dto;
    }
}
