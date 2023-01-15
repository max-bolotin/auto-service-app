package com.example.autoserviceapp.dto.response;

import lombok.Data;

@Data
public class CarResponseDto {
    private Long id;
    private String manufacturer;
    private String model;
    private int makeYear;
    private String registrationNumber;
    private Long clientId;
}
