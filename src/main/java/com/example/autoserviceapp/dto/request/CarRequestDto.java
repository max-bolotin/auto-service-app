package com.example.autoserviceapp.dto.request;

import lombok.Data;

@Data
public class CarRequestDto {
    private String manufacturer;
    private String model;
    private int makeYear;
    private String registrationNumber;
    private Long clientId;
}
