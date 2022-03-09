package com.example.ecommerceweb.repository;

import com.example.ecommerceweb.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(int id);
}
