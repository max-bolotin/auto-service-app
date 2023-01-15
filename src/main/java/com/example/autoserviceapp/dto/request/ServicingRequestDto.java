package com.example.autoserviceapp.dto.request;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ServicingRequestDto {
    private String name;
    private Long orderId;
    private Long workerId;
    private BigDecimal price;
    private boolean isSalaryPaid;
}
