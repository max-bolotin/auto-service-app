package com.example.autoserviceapp.dto.request;

import java.util.List;
import lombok.Data;

@Data
public class ClientRequestDto {
    private String name;
    private List<Long> carIds;
    private List<Long> orderIds;
}
