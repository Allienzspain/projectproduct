package com.Product.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Product.model.ProductType;
import com.Product.repository.ProductTypeRepo;

@ExtendWith(MockitoExtension.class)
public class ProductTypeServiceImplTest {

    @Mock
    private ProductTypeRepo productTypeRepoMock;

    @InjectMocks
    private ProductTypeServiceType productTypeService;

    private ProductType testProductType;

    @BeforeEach
    public void setUp() {
        testProductType = new ProductType();
        testProductType.setId(1);
        testProductType.setTypeName("Test Type");
    }

    @Test
    public void testCreateProductType_AdminPermission_Success() throws Exception {
        when(productTypeRepoMock.save(any(ProductType.class))).thenReturn(testProductType);
        ProductType result = productTypeService.createProductType(testProductType);
        assertEquals(testProductType, result);
    }

    @Test
    public void testGetProductTypeById_AdminPermission_Success() {
        when(productTypeRepoMock.findById(anyInt())).thenReturn(Optional.of(testProductType));
        ProductType result = productTypeService.getProductTypeById(1);

        assertEquals(testProductType, result);
    }

    @Test
    public void testGetAllProductTypes_AdminPermission_Success() {
        List<ProductType> expectedProductTypes = Collections.singletonList(testProductType);
        when(productTypeRepoMock.findAll()).thenReturn(expectedProductTypes);

        List<ProductType> result = productTypeService.getAllProductTypes();

        assertEquals(expectedProductTypes, result);
    }

    @Test
    public void testUpdateProductType_AdminPermission_Success() throws Exception {
        ProductType updatedProductType = new ProductType();
        updatedProductType.setId(1);
        updatedProductType.setTypeName("Updated Test Type");
        when(productTypeRepoMock.findById(anyInt())).thenReturn(Optional.of(testProductType));
        when(productTypeRepoMock.save(any(ProductType.class))).thenReturn(updatedProductType);
        ProductType result = productTypeService.updateProductType(1, updatedProductType);
        assertEquals(updatedProductType, result);
    }

}
