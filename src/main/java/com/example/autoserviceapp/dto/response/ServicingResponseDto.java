package com.example.autoserviceapp.dto.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ServicingResponseDto {
    private Long id;
    private String name;
    private Long orderId;
    private Long workerId;
    private BigDecimal price;
    private boolean isSalaryPaid;
}
