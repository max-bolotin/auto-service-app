package com.example.autoserviceapp.controller;

import com.example.autoserviceapp.dto.mapper.ProductMapper;
import com.example.autoserviceapp.dto.request.ProductRequestDto;
import com.example.autoserviceapp.dto.response.ProductResponseDto;
import com.example.autoserviceapp.model.Product;
import com.example.autoserviceapp.service.ProductService;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductMapper productMapper;
    private final ProductService productService;

    public ProductController(ProductMapper productMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @PostMapping("/new")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.mapToModel(productRequestDto);
        product = productService.save(product);
        return productMapper.mapToDto(product);
    }

    @PutMapping("/update")
    public ProductResponseDto updateProduct(@RequestParam(name = "product-id") Long productId,
            @RequestParam(name = "price") BigDecimal price) {
        Product product = productService.update(productId, price);
        return productMapper.mapToDto(product);
    }
}
