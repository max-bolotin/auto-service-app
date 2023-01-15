package com.example.autoserviceapp.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductDiscount {
    private BigDecimal productDiscount;
    private Client client;

    public BigDecimal getProductDiscount() {
        return BigDecimal.valueOf(client.getOrders().size() * 0.01F);
    }
}
