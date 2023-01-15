package com.example.autoserviceapp.repository;

import com.example.autoserviceapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
