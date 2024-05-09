package com.Product.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void testProductConstructor() {

        int id = 1;
        String name = "Test Product";
        String description = "Test Description";
        String price = "100";
        int stock = 10;
        ProductType type = new ProductType();

        Product product = new Product(id, name, description, price, stock, type);

        assertEquals(id, product.getIdProduct());
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice());
        assertEquals(stock, product.getStock());
        assertEquals(type, product.getType());
    }

    @Test
    public void testProductSetters() {
        Product product = new Product();
        int id = 1;
        String name = "Test Product";
        String description = "Test Description";
        String price = "100";
        int stock = 10;
        ProductType type = new ProductType();

        product.setIdProduct(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setType(type);

        assertEquals(id, product.getIdProduct());
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice());
        assertEquals(stock, product.getStock());
        assertEquals(type, product.getType());
    }
}
