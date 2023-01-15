package com.example.autoserviceapp.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class OrderPrice {
    private static final double SERVICING_DISCOUNT = 0.02;
    private static final double PRODUCT_DISCOUNT = 0.01;
    private Order order;

    public OrderPrice(Order order) {
        this.order = order;
    }

    public BigDecimal getOrderPrice() {
        BigDecimal finalPrice = BigDecimal.ZERO;
        Client client = order.getCar().getOwner();
        int clientsOrders = client.getOrders().size();
        List<Servicing> servicings = order.getServicings();
        if (servicings.size() > 1) {
            for (int i = 1; i < servicings.size(); i++) {
                finalPrice = finalPrice.add(servicings.get(i).getPrice().subtract(
                        BigDecimal.valueOf(clientsOrders * SERVICING_DISCOUNT)));
            }
        } else {
            finalPrice = servicings.get(0).getPrice().subtract(
                    BigDecimal.valueOf(clientsOrders * SERVICING_DISCOUNT));
        }
        List<Product> products = order.getProducts();
        for (Product product : products) {
            finalPrice = finalPrice.add(product.getPrice().subtract(
                    BigDecimal.valueOf(clientsOrders * PRODUCT_DISCOUNT)));
        }
        return finalPrice;
    }
}
