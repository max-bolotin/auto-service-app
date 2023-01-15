package com.example.autoserviceapp.service.impl;

import com.example.autoserviceapp.model.Order;
import com.example.autoserviceapp.model.OrderPrice;
import com.example.autoserviceapp.model.OrderStatus;
import com.example.autoserviceapp.model.Product;
import com.example.autoserviceapp.model.Servicing;
import com.example.autoserviceapp.repository.OrderRepository;
import com.example.autoserviceapp.repository.ProductRepository;
import com.example.autoserviceapp.repository.ServicingRepository;
import com.example.autoserviceapp.service.OrderService;
import com.example.autoserviceapp.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private static final double SERVICING_DISCOUNT = 0.02;
    private static final double PRODUCT_DISCOUNT = 0.01;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ServicingRepository servicingRepository;
    private final ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductRepository productRepository,
                            ServicingRepository servicingRepository,
                            ProductService productService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.servicingRepository = servicingRepository;
        this.productService = productService;
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No order found with id " + id));
    }

    @Override
    public List<Order> getAllById(List<Long> orderIds) {
        return orderRepository.findAllById(orderIds);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order addProduct(Long orderId, Long productId) {
        Order order = orderRepository.getById(orderId);
        List<Product> products = order.getProducts();
        Product product = productService.findById(productId);
        if (!products.contains(product)) {
            products.add(product);
        }
        order.setProducts(products);
        return save(order);
    }

    @Override
    public Order addServicing(Long orderId, Long servicingId) {
        Order order = orderRepository.getById(orderId);
        List<Servicing> servicings = order.getServicings();
        Servicing servicing = servicingRepository.findById(servicingId).orElseThrow(
                () -> new RuntimeException("No servicing found with id: " + servicingId));
        if (!servicings.contains(servicing)) {
            servicings.add(servicing);
        }
        order.setServicings(servicings);
        return save(order);
    }

    @Override
    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.getById(orderId);
        OrderStatus orderStatus;
        try {
            orderStatus = OrderStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Status " + status + " not valid! ",e);
        }
        order.setOrderStatus(orderStatus);
        return orderRepository.save(order);
    }

    @Override
    public Order calculateOrderPrice(Long orderId) {
        Order order = orderRepository.getById(orderId);
        OrderPrice finalPrice = new OrderPrice(order);
        order.setFinalPrice(finalPrice.getOrderPrice());
        return save(order);
    }
}
