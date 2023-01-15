package com.example.autoserviceapp.service.impl;

import com.example.autoserviceapp.model.Car;
import com.example.autoserviceapp.repository.CarRepository;
import com.example.autoserviceapp.service.CarService;
import com.example.autoserviceapp.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository repository;
    private final ClientService clientService;

    public CarServiceImpl(CarRepository repository, ClientService clientService) {
        this.repository = repository;
        this.clientService = clientService;
    }

    @Override
    public Car findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No car found with id " + id));
    }

    @Override
    public Car save(Car car) {
        return repository.save(car);
    }

    @Override
    public Car addClient(Long carId, Long clientId) {
        Car car = findById(carId);
        car.setOwner(clientService.findById(clientId));
        return save(car);
    }
}
