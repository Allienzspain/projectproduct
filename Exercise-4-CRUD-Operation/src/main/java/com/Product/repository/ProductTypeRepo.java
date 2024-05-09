package com.Product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Product.model.ProductType;

@Repository
public interface ProductTypeRepo extends JpaRepository<ProductType, Integer> {
    // List<Product> findByTypeName(String typeName);

}
