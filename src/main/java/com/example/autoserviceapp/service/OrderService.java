package com.example.autoserviceapp.service;

import com.example.autoserviceapp.model.Order;
import java.util.List;

public interface OrderService {
    Order findById(Long id);

    List<Order> getAllById(List<Long> orderIds);

    Order save(Order order);

    Order addProduct(Long orderId, Long productId);

    Order addServicing(Long orderId, Long servicingId);

    Order updateOrderStatus(Long orderId, String status);

    Order calculateOrderPrice(Long orderId);
}
