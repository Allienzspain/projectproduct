package com.Product.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class ProductTypeTest {

    @Test
    public void testProductTypeConstructor() {
        int id = 1;
        String typeName = "Test Type";
        Set<Product> products = new LinkedHashSet<>();

        ProductType productType = new ProductType(id, typeName, products);

        assertEquals(id, productType.getId());
        assertEquals(typeName, productType.getTypeName());
        assertEquals(products, productType.getProducts());
    }

    @Test
    public void testProductTypeSetters() {
        ProductType productType = new ProductType();
        int id = 1;
        String typeName = "Test Type";
        Set<Product> products = new LinkedHashSet<>();

        productType.setId(id);
        productType.setTypeName(typeName);
        productType.setProducts(products);

        assertEquals(id, productType.getId());
        assertEquals(typeName, productType.getTypeName());
        assertEquals(products, productType.getProducts());
    }

}
