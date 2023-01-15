package com.example.autoserviceapp.service.impl;

import com.example.autoserviceapp.model.Product;
import com.example.autoserviceapp.repository.ProductRepository;
import com.example.autoserviceapp.service.ProductService;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find product with id: " + id));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long productId, BigDecimal price) {
        Product product = findById(productId);
        product.setPrice(price);
        return save(product);
    }
}
