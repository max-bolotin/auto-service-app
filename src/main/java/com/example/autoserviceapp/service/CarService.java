package com.example.autoserviceapp.service;

import com.example.autoserviceapp.model.Car;

public interface CarService {
    Car findById(Long id);

    Car save(Car car);

    Car addClient(Long carId, Long clientId);
}
