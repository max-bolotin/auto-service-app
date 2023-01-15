package com.example.autoserviceapp.dto.mapper;

import com.example.autoserviceapp.dto.request.OrderRequestDto;
import com.example.autoserviceapp.dto.response.OrderResponseDto;
import com.example.autoserviceapp.model.Order;
import com.example.autoserviceapp.model.OrderStatus;
import com.example.autoserviceapp.model.Product;
import com.example.autoserviceapp.model.Servicing;
import com.example.autoserviceapp.service.CarService;
import com.example.autoserviceapp.service.ProductService;
import com.example.autoserviceapp.service.ServicingService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements RequestDtoMapper<OrderRequestDto, Order>,
        ResponseDtoMapper<OrderResponseDto, Order> {
    private final CarService carService;
    private final ProductService productService;
    private final ServicingService servicingService;

    public OrderMapper(CarService carService,
                       ProductService productService,
                       ServicingService servicingService) {
        this.carService = carService;
        this.productService = productService;
        this.servicingService = servicingService;
    }

    @Override
    public Order mapToModel(OrderRequestDto dto) {
        Order order = new Order();
        order.setCar(carService.findById(dto.getCarId()));
        order.setOrderDate(dto.getOrderDate());
        order.setProblemDescription(dto.getProblemDescription());
        //        order.setFinalPrice(dto.getFinalPrice());
        order.setFulfillmentDate(dto.getFulfillmentDate());
        /*        order.setProducts(dto.getProductIds().stream()
                .map(productService::findById)
                .collect(Collectors.toList()));*/
        order.setServicings(dto.getServicingIds().stream()
                .map(servicingService::findById)
                .collect(Collectors.toList()));
        order.setOrderStatus(OrderStatus.valueOf(
                String.valueOf(dto.getOrderStatus()).toUpperCase()));
        return order;
    }

    @Override
    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setProblemDescription(order.getProblemDescription());
        dto.setCarId(order.getCar().getId());
        dto.setFulfillmentDate(order.getFulfillmentDate());
        if (order.getFinalPrice() != null) {
            dto.setFinalPrice(order.getFinalPrice());
        }
        if (order.getProducts() != null) {
            dto.setProductIds(order.getProducts()
                    .stream()
                    .map(Product::getId)
                    .collect(Collectors.toList()));
        }
        dto.setServicingIds(order.getServicings()
                .stream()
                .map(Servicing::getId)
                .collect(Collectors.toList()));
        dto.setOrderStatus(order.getOrderStatus());
        return dto;
    }
}
