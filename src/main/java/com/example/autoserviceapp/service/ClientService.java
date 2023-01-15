package com.example.autoserviceapp.service;

import com.example.autoserviceapp.model.Client;

public interface ClientService {
    Client findById(Long id);

    Client save(Client client);

    Client addOrderToClient(Long clientId, Long orderId);
}
