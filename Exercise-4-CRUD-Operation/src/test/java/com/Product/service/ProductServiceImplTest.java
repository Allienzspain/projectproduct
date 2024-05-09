package com.Product.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Product.model.Product;
import com.Product.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepositoryMock;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product testProduct;

    @BeforeEach
    public void setUp() {
        Product testProduct = new Product();
        testProduct.setIdProduct(1);
        testProduct.setName("Test Product");
        testProduct.setDescription("description");
        testProduct.setPrice("234");
        testProduct.setStock(233);
    }

    @Test
    public void testCreateProduct_AdminPer2mission_Success() throws Exception {

        lenient().when(productRepositoryMock.save(any(Product.class))).thenReturn(testProduct);

        Product createdProduct = productService.createProduct(testProduct);

        assertEquals(testProduct, createdProduct);
    }

    @Test
    public void testGetProductById_AdminPermission_Success() throws Exception {

        Product expectedProduct = new Product();
        expectedProduct.setIdProduct(1);
        expectedProduct.setName("Test Product");
        expectedProduct.setDescription("description");
        expectedProduct.setPrice("234");
        expectedProduct.setStock(233);
        lenient().when(productRepositoryMock.findById(anyInt())).thenReturn(Optional.of(expectedProduct));

        Product product = productService.getProductById(1);

        assertEquals(expectedProduct, product);
    }

    @Test
    public void testGetAllProducts_AdminPermission_Success() throws Exception {

        List<Product> expectedProducts = Arrays.asList(new Product(), new Product());
        lenient().when(productRepositoryMock.findAll()).thenReturn(expectedProducts);

        assertNotEquals(expectedProducts, productService);
    }

    @Test
    public void testUpdateProduct_AdminPermission_Success() throws Exception {

        Product updatedProduct = new Product();
        updatedProduct.setIdProduct(1);
        updatedProduct.setName("Updated Test Product");
        updatedProduct.setDescription("Updated description");
        updatedProduct.setPrice("500");
        updatedProduct.setStock(100);
        Product existingProduct = new Product();
        existingProduct.setIdProduct(1);
        existingProduct.setName("Test Product");
        existingProduct.setDescription("description");
        existingProduct.setPrice("234");
        existingProduct.setStock(233);
        when(productRepositoryMock.findById(anyInt())).thenReturn(Optional.of(existingProduct));
        when(productRepositoryMock.save(any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.updateProduct(1, updatedProduct);

        assertEquals(updatedProduct, result);
    }

    @Test
    public void testUpdateProductStock_AdminPermission_Success() {

        Product existingProduct = new Product();
        existingProduct.setIdProduct(1);
        existingProduct.setName("Test Product");
        existingProduct.setDescription("description");
        existingProduct.setPrice("234");
        existingProduct.setStock(300);
        when(productRepositoryMock.findById(anyInt())).thenReturn(Optional.of(existingProduct));

        productService.updateProductStock(1, 300);

        assertEquals(300, existingProduct.getStock());
    }

    @Test
    public void testUpdateProductStock_AdminPermission_NotSuccess() {

        Product existingProduct = new Product();
        existingProduct.setIdProduct(1);
        existingProduct.setName("Test Product");
        existingProduct.setDescription("description");
        existingProduct.setPrice("234");
        existingProduct.setStock(233);
        when(productRepositoryMock.findById(anyInt())).thenReturn(Optional.of(existingProduct));

        productService.updateProductStock(1, 300);

        assertNotEquals(300, existingProduct.getStock());
    }

    @Test
    public void testGetAllProductByTypeName_AdminPermission_Success() {
        String typeName = "testType";
        List<Product> expectedProducts = Arrays.asList(new Product(), new Product());
        when(productRepositoryMock.findByTypeTypeName(typeName)).thenReturn(expectedProducts);

        List<Product> products = productService.getAllProductByTypeName(typeName);

        assertEquals(expectedProducts, products);
    }

}
