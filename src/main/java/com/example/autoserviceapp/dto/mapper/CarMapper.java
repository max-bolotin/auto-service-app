package com.example.autoserviceapp.dto.mapper;

import com.example.autoserviceapp.dto.request.CarRequestDto;
import com.example.autoserviceapp.dto.response.CarResponseDto;
import com.example.autoserviceapp.model.Car;
import com.example.autoserviceapp.service.CarService;
import com.example.autoserviceapp.service.ClientService;
import org.springframework.stereotype.Component;

@Component
public class CarMapper implements RequestDtoMapper<CarRequestDto, Car>,
        ResponseDtoMapper<CarResponseDto, Car> {
    private final CarService carService;
    private final ClientService clientService;

    public CarMapper(CarService carService, ClientService clientService) {
        this.carService = carService;
        this.clientService = clientService;
    }

    @Override
    public Car mapToModel(CarRequestDto dto) {
        Car car = new Car();
        car.setModel(dto.getModel());
        car.setManufacturer(dto.getManufacturer());
        car.setMakeYear(dto.getMakeYear());
        car.setRegistrationNumber(dto.getRegistrationNumber());
        //        car.setOwner(clientService.findById(dto.getClientId()));
        return car;
    }

    @Override
    public CarResponseDto mapToDto(Car car) {
        CarResponseDto dto = new CarResponseDto();
        dto.setId(car.getId());
        if (car.getOwner() != null) {
            dto.setClientId(car.getOwner().getId());
        }
        dto.setModel(car.getModel());
        dto.setManufacturer(car.getManufacturer());
        dto.setMakeYear(car.getMakeYear());
        dto.setRegistrationNumber(car.getRegistrationNumber());
        return dto;
    }
}
