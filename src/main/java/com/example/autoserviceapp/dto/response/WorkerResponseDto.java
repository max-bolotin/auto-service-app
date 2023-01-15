package com.example.autoserviceapp.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class WorkerResponseDto {
    private Long id;
    private String name;
    private List<Long> fulfilledOrderIds;
}
