package com.product.purchase.service.PurchaseService.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.product.purchase.service.PurchaseService.dto.ProductDto;

class ProductServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductById_Success() throws Exception {
        int productId = 1;
        String jwt = "dummy_jwt";
        ProductDto expectedProduct = new ProductDto(productId, "Product", "Description", "10.00", 100, null);

        ResponseEntity<ProductDto> responseEntity = new ResponseEntity<>(expectedProduct, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(org.springframework.http.HttpMethod.GET), any(),
                eq(ProductDto.class)))
                .thenReturn(responseEntity);

        ProductDto actualProduct = productService.getProductById(productId, jwt);

        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void testGetProductById_Failure() throws Exception {
        int productId = 1;
        String jwt = "dummy_jwt";

        ResponseEntity<ProductDto> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        when(restTemplate.exchange(anyString(), eq(org.springframework.http.HttpMethod.GET), any(),
                eq(ProductDto.class)))
                .thenReturn(responseEntity);

        assertThrows(RuntimeException.class, () -> productService.getProductById(productId, jwt));
    }

    @Test
    void testGetPurchasesByProductType_Success() throws Exception {
        String productType = "Type";
        String jwt = "dummy_jwt";
        List<ProductDto> expectedProducts = Arrays.asList(new ProductDto(1, "Product1", "Desc1", "10.00", 100, null),
                new ProductDto(2, "Product2", "Desc2", "20.00", 200, null));

        ResponseEntity<List<ProductDto>> responseEntity = new ResponseEntity<>(expectedProducts, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(org.springframework.http.HttpMethod.GET), any(),
                any(ParameterizedTypeReference.class))).thenReturn(responseEntity);

        List<ProductDto> actualProducts = productService.getPurchasesByProductType(productType, jwt);

        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    void testGetPurchasesByProductType_Failure() throws Exception {
        String productType = "Type";
        String jwt = "dummy_jwt";

        ResponseEntity<List<ProductDto>> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        when(restTemplate.exchange(anyString(), eq(org.springframework.http.HttpMethod.GET), any(),
                any(ParameterizedTypeReference.class))).thenReturn(responseEntity);

        assertThrows(RuntimeException.class, () -> productService.getPurchasesByProductType(productType, jwt));
    }

    @Test
    void testUpdateProductStock_Success() throws Exception {

        int productId = 1;
        int newStock = 50;
        String jwt = "dummy_jwt";
        ProductDto expectedProduct = new ProductDto(productId, "Product", "Description", "10.00", newStock, null);

        ResponseEntity<ProductDto> responseEntity = new ResponseEntity<>(expectedProduct, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(org.springframework.http.HttpMethod.PUT), any(),
                eq(ProductDto.class)))
                .thenReturn(responseEntity);

        ProductDto actualProduct = productService.updateProductStock(productId, newStock, jwt);

        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void testUpdateProductStock_Failure() throws Exception {
        int productId = 1;
        int newStock = 50;
        String jwt = "dummy_jwt";

        ResponseEntity<ProductDto> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        when(restTemplate.exchange(anyString(), eq(org.springframework.http.HttpMethod.PUT), any(),
                eq(ProductDto.class)))
                .thenReturn(responseEntity);

        assertThrows(RuntimeException.class, () -> productService.updateProductStock(productId, newStock, jwt));
    }
}
