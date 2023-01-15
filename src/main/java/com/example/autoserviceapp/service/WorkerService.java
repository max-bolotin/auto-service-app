package com.example.autoserviceapp.service;

import com.example.autoserviceapp.model.Order;
import com.example.autoserviceapp.model.Worker;
import java.math.BigDecimal;
import java.util.List;

public interface WorkerService {
    Worker findById(Long id);

    Worker save(Worker worker);

    Worker addOrder(Long workerId, Long orderId);

    List<Order> getOrdersByWorkerId(Long workerId);

    BigDecimal calculateSalaryByWorkerId(Long workerId);
}
