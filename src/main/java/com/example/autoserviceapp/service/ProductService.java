package com.example.autoserviceapp.service;

import com.example.autoserviceapp.model.Product;
import java.math.BigDecimal;

public interface ProductService {
    Product findById(Long id);

    Product save(Product product);

    Product update(Long productId, BigDecimal price);
}
