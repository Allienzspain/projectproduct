package com.product.purchase.service.PurchaseService.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductTypeDtoTest {

    @Test
    void testProductsNotNull() {
        ProductTypeDto type = new ProductTypeDto();

        assertNotNull(type.getProducts());
    }

    @Test
    void testProductsEmptyInitially() {
        ProductTypeDto type = new ProductTypeDto();

        assertEquals(0, type.getProducts().size());
    }
}
