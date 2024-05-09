package com.Product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    // List<Product> findByType(ProductType type);

    List<Product> findByTypeTypeName(String typeName);

}
