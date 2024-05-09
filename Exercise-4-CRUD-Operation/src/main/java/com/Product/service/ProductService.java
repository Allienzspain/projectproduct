package com.Product.service;

import java.util.List;

import com.Product.model.Product;

public interface ProductService {

    Product createProduct(Product product) throws Exception;

    Product getProductById(int id) throws Exception;

    List<Product> getAllProducts();

    Product updateProduct(int id, Product updatedProduct) throws Exception;

    void updateProductStock(int productId, int newStock);

    List<Product> getAllProductByTypeName(String typeName);

}
