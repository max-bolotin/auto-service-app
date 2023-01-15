package com.example.autoserviceapp.controller;

import com.example.autoserviceapp.dto.mapper.CarMapper;
import com.example.autoserviceapp.dto.request.CarRequestDto;
import com.example.autoserviceapp.dto.response.CarResponseDto;
import com.example.autoserviceapp.model.Car;
import com.example.autoserviceapp.service.CarService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarMapper carMapper;
    private final CarService carService;

    public CarController(CarMapper carMapper, CarService carService) {
        this.carMapper = carMapper;
        this.carService = carService;
    }

    @PostMapping("/new")
    public CarResponseDto saveCar(@RequestBody CarRequestDto carRequestDto) {
        Car car = carMapper.mapToModel(carRequestDto);
        carService.save(car);
        return carMapper.mapToDto(car);
    }

    @PutMapping("/update")
    public CarResponseDto updateCar(@RequestParam(name = "car-id") Long carId,
                         @RequestParam(name = "client-id") Long clientId) {
        Car car = carService.addClient(carId, clientId);
        return carMapper.mapToDto(car);
    }
}
