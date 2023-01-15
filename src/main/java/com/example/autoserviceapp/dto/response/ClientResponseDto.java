package com.example.autoserviceapp.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class ClientResponseDto {
    private Long id;
    private String name;
    private List<Long> carIds;
    private List<Long> orderIds;
}
