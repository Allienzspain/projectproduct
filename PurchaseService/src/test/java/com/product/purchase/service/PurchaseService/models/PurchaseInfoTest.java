package com.product.purchase.service.PurchaseService.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PurchaseInfoTest {

    @Test
    void testEquals_SameObjects() {
        PurchaseInfo purchase1 = new PurchaseInfo(1, 1, 1, 2, "Product A", "Description A", "10.00");
        PurchaseInfo purchase2 = purchase1;

        assertEquals(purchase1, purchase2);
    }

    @Test
    void testEquals_DifferentObjects_SameValues() {
        PurchaseInfo purchase1 = new PurchaseInfo(1, 1, 1, 2, "Product A", "Description A", "10.00");
        PurchaseInfo purchase2 = new PurchaseInfo(1, 1, 1, 2, "Product A", "Description A", "10.00");

        assertEquals(purchase1, purchase2);
    }

    @Test
    void testEquals_DifferentObjects_DifferentValues() {
        PurchaseInfo purchase1 = new PurchaseInfo(1, 1, 1, 2, "Product A", "Description A", "10.00");
        PurchaseInfo purchase2 = new PurchaseInfo(2, 1, 1, 2, "Product B", "Description B", "20.00");

        assertNotEquals(purchase1, purchase2);
    }

    @Test
    void testHashCode_SameObjects() {
        PurchaseInfo purchase1 = new PurchaseInfo(1, 1, 1, 2, "Product A", "Description A", "10.00");
        PurchaseInfo purchase2 = purchase1;

        assertEquals(purchase1.hashCode(), purchase2.hashCode());
    }

    @Test
    void testHashCode_DifferentObjects_SameValues() {
        PurchaseInfo purchase1 = new PurchaseInfo(1, 1, 1, 2, "Product A", "Description A", "10.00");
        PurchaseInfo purchase2 = new PurchaseInfo(1, 1, 1, 2, "Product A", "Description A", "10.00");

        assertEquals(purchase1.hashCode(), purchase2.hashCode());
    }

    @Test
    void testHashCode_DifferentObjects_DifferentValues() {
        PurchaseInfo purchase1 = new PurchaseInfo(1, 1, 1, 2, "Product A", "Description A", "10.00");
        PurchaseInfo purchase2 = new PurchaseInfo(2, 1, 1, 2, "Product B", "Description B", "20.00");

        assertNotEquals(purchase1.hashCode(), purchase2.hashCode());
    }
}
