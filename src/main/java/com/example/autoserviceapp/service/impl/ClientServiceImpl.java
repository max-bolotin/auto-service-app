package com.example.autoserviceapp.service.impl;

import com.example.autoserviceapp.model.Client;
import com.example.autoserviceapp.model.Order;
import com.example.autoserviceapp.repository.ClientRepository;
import com.example.autoserviceapp.service.ClientService;
import com.example.autoserviceapp.service.OrderService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final OrderService orderService;

    public ClientServiceImpl(ClientRepository clientRepository, OrderService orderService) {
        this.clientRepository = clientRepository;
        this.orderService = orderService;
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No client found with id " + id));
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client addOrderToClient(Long clientId, Long orderId) {
        Client client = findById(clientId);
        List<Order> orders = client.getOrders();
        Order order = orderService.findById(orderId);
        if (!orders.contains(order)) {
            orders.add(order);
        }
        client.setOrders(orders);
        return save(client);
    }
}
