package com.example.autoserviceapp.dto.request;

import com.example.autoserviceapp.model.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class OrderRequestDto {
    private Long carId;
    private String problemDescription;
    private LocalDateTime orderDate;
    private BigDecimal finalPrice;
    private LocalDateTime fulfillmentDate;
    private List<Long> servicingIds;
    private List<Long> productIds;
    private OrderStatus orderStatus;
}
