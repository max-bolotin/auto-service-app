package com.example.autoserviceapp.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class WorkerSalary {
    private BigDecimal salary;
    private Servicing servicing;

    public BigDecimal getSalary() {
        return servicing.getPrice().multiply(BigDecimal.valueOf(0.4F));
    }
}
