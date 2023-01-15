package com.example.autoserviceapp.dto.mapper;

import com.example.autoserviceapp.dto.request.ClientRequestDto;
import com.example.autoserviceapp.dto.response.ClientResponseDto;
import com.example.autoserviceapp.model.Car;
import com.example.autoserviceapp.model.Client;
import com.example.autoserviceapp.model.Order;
import com.example.autoserviceapp.service.CarService;
import com.example.autoserviceapp.service.OrderService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper implements RequestDtoMapper<ClientRequestDto, Client>,
        ResponseDtoMapper<ClientResponseDto, Client> {
    private final CarService carService;
    private final OrderService orderService;

    public ClientMapper(CarService carService, OrderService orderService) {
        this.carService = carService;
        this.orderService = orderService;
    }

    @Override
    public Client mapToModel(ClientRequestDto dto) {
        Client client = new Client();
        client.setName(dto.getName());
        client.setCars(dto.getCarIds().stream()
                .map(carService::findById)
                .collect(Collectors.toList()));
        /*client.setOrders((dto.getOrderIds().stream()
                .map(orderService::findById)
                .collect(Collectors.toList())));*/
        return client;
    }

    @Override
    public ClientResponseDto mapToDto(Client client) {
        ClientResponseDto dto = new ClientResponseDto();
        dto.setId(client.getId());
        dto.setName(client.getName());
        if (client.getOrders() != null) {
            dto.setOrderIds(client.getOrders()
                    .stream()
                    .map(Order::getId)
                    .collect(Collectors.toList()));
        }
        dto.setCarIds(client.getCars()
                .stream()
                .map(Car::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
