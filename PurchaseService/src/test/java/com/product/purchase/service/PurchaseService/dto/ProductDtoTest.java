package com.product.purchase.service.PurchaseService.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductDtoTest {

    @Test
    void testEquals_SameObjects() {
        ProductDto product1 = new ProductDto(1, "Product A", "Description A", "10.00", 100, new ProductTypeDto());
        ProductDto product2 = product1;

        assertEquals(product1, product2);
    }

    @Test
    void testEquals_DifferentObjects_DifferentValues() {
        ProductDto product1 = new ProductDto(1, "Product A", "Description A", "10.00", 100, new ProductTypeDto());
        ProductDto product2 = new ProductDto(2, "Product B", "Description B", "20.00", 200, new ProductTypeDto());

        assertNotEquals(product1, product2);
    }

    @Test
    void testHashCode_SameObjects() {
        ProductDto product1 = new ProductDto(1, "Product A", "Description A", "10.00", 100, new ProductTypeDto());
        ProductDto product2 = product1;

        assertEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    void testHashCode_DifferentObjects_DifferentValues() {
        ProductDto product1 = new ProductDto(1, "Product A", "Description A", "10.00", 100, new ProductTypeDto());
        ProductDto product2 = new ProductDto(2, "Product B", "Description B", "20.00", 200, new ProductTypeDto());

        assertNotEquals(product1.hashCode(), product2.hashCode());
    }
}
