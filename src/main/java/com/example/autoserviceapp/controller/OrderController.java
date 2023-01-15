package com.example.autoserviceapp.controller;

import com.example.autoserviceapp.dto.mapper.OrderMapper;
import com.example.autoserviceapp.dto.request.OrderRequestDto;
import com.example.autoserviceapp.dto.response.OrderResponseDto;
import com.example.autoserviceapp.model.Order;
import com.example.autoserviceapp.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderMapper orderMapper;
    private final OrderService orderService;

    public OrderController(OrderMapper orderMapper, OrderService orderService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }

    @PostMapping("/new")
    public OrderResponseDto addOrder(@RequestBody OrderRequestDto orderRequestDto) {
        Order order = orderMapper.mapToModel(orderRequestDto);
        order = orderService.save(order);
        return orderMapper.mapToDto(order);
    }

    @PostMapping("/add-product")
    public OrderResponseDto addProductById(@RequestParam(name = "Order-id") Long orderId,
                                @RequestParam(name = "product-id") Long productId) {
        Order order = orderService.addProduct(orderId, productId);
        return orderMapper.mapToDto(order);
    }

    @PostMapping("/add-servicing")
    public OrderResponseDto addServicingById(@RequestParam(name = "order-id") Long orderId,
                                @RequestParam(name = "servicing-id") Long servicingId) {
        Order order = orderService.addServicing(orderId, servicingId);
        return orderMapper.mapToDto(order);
    }

    @PutMapping("/order-status")
    public OrderResponseDto orderStatusUpdate(@RequestParam(name = "order-id") Long orderId,
                                  @RequestParam(name = "status") String orderStatus) {
        Order order = orderService.updateOrderStatus(orderId, orderStatus);
        return orderMapper.mapToDto(order);
    }

    @PutMapping("/order-price")
    public OrderResponseDto calculateAndSaveOrderPrice(@RequestParam("order-id") Long orderId) {
        Order order = orderService.calculateOrderPrice(orderId);
        return orderMapper.mapToDto(order);
    }

    //Test
    @GetMapping("/{id}")
    public OrderResponseDto getOrder(@PathVariable Long id) {
        return orderMapper.mapToDto(orderService.findById(id));
    }
}
