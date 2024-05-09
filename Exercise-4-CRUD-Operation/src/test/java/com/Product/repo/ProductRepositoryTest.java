package com.Product.repo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Product.model.Product;
import com.Product.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepositoryMock;

    @Test
    void testFindByTypeTypeName() {
        String typeName = "Test Type";
        List<Product> expectedProducts = new ArrayList<>();
        Product product1 = new Product();
        product1.setIdProduct(1);
        product1.setName("Product 1");
        Product product2 = new Product();
        product2.setIdProduct(2);
        product2.setName("Product 2");
        expectedProducts.add(product1);
        expectedProducts.add(product2);

        when(productRepositoryMock.findByTypeTypeName(typeName)).thenReturn(expectedProducts);

        List<Product> actualProducts = productRepositoryMock.findByTypeTypeName(typeName);

        assertEquals(expectedProducts.size(), actualProducts.size());
        for (int i = 0; i < expectedProducts.size(); i++) {
            assertEquals(expectedProducts.get(i).getIdProduct(), actualProducts.get(i).getIdProduct());
            assertEquals(expectedProducts.get(i).getName(), actualProducts.get(i).getName());

        }
    }

    @Test
    void testFindById() {
        int productId = 1;
        Product expectedProduct = new Product();
        expectedProduct.setIdProduct(productId);
        expectedProduct.setName("Test Product");

        when(productRepositoryMock.findById(productId)).thenReturn(Optional.of(expectedProduct));

        Optional<Product> actualProductOptional = productRepositoryMock.findById(productId);

        assertTrue(actualProductOptional.isPresent());
        Product actualProduct = actualProductOptional.get();
        assertEquals(expectedProduct.getIdProduct(), actualProduct.getIdProduct());
        assertEquals(expectedProduct.getName(), actualProduct.getName());
    }
}
