package com.example.autoserviceapp.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ServicingDiscount {
    private BigDecimal servicingDiscount;
    private Client client;

    public BigDecimal getServicingDiscount() {
        return BigDecimal.valueOf(client.getOrders().size() * 0.02F);
    }
}
