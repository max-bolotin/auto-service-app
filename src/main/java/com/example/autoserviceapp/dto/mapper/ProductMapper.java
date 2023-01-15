package com.example.autoserviceapp.dto.mapper;

import com.example.autoserviceapp.dto.request.ProductRequestDto;
import com.example.autoserviceapp.dto.response.ProductResponseDto;
import com.example.autoserviceapp.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements RequestDtoMapper<ProductRequestDto, Product>,
        ResponseDtoMapper<ProductResponseDto, Product> {
    @Override
    public Product mapToModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setPrice(dto.getPrice());
        product.setName(dto.getName());
        return product;
    }

    @Override
    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setPrice(product.getPrice());
        dto.setName(product.getName());
        return dto;
    }
}
