package com.example.autoserviceapp.dto.response;

import com.example.autoserviceapp.model.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private Long carId;
    private String problemDescription;
    private LocalDateTime orderDate;
    private BigDecimal finalPrice;
    private LocalDateTime fulfillmentDate;
    private List<Long> servicingIds;
    private List<Long> productIds;
    private OrderStatus orderStatus;
}
