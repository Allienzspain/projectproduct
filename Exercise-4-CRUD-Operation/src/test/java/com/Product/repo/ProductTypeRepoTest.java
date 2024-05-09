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

import com.Product.model.ProductType;
import com.Product.repository.ProductTypeRepo;

@ExtendWith(MockitoExtension.class)
class ProductTypeRepoTest {

    @Mock
    private ProductTypeRepo productTypeRepoMock;

    @Test
    void testFindById() {
        int productTypeId = 1;
        ProductType expectedProductType = new ProductType();
        expectedProductType.setId(productTypeId);
        expectedProductType.setTypeName("Test Type");

        when(productTypeRepoMock.findById(productTypeId)).thenReturn(Optional.of(expectedProductType));

        Optional<ProductType> actualProductTypeOptional = productTypeRepoMock.findById(productTypeId);

        assertTrue(actualProductTypeOptional.isPresent());
        ProductType actualProductType = actualProductTypeOptional.get();
        assertEquals(expectedProductType.getId(), actualProductType.getId());
        assertEquals(expectedProductType.getTypeName(), actualProductType.getTypeName());
    }

    @Test
    void testFindAll() {
        List<ProductType> expectedProductTypes = new ArrayList<>();
        expectedProductTypes.add(new ProductType(1, "Type 1", null));
        expectedProductTypes.add(new ProductType(2, "Type 2", null));

        when(productTypeRepoMock.findAll()).thenReturn(expectedProductTypes);

        List<ProductType> actualProductTypes = productTypeRepoMock.findAll();

        assertEquals(expectedProductTypes.size(), actualProductTypes.size());
        for (int i = 0; i < expectedProductTypes.size(); i++) {
            assertEquals(expectedProductTypes.get(i), actualProductTypes.get(i));
        }
    }

    @Test
    void testSaveProductType() {

        ProductType productTypeToSave = new ProductType(1, "New Type", null);
        when(productTypeRepoMock.save(productTypeToSave)).thenReturn(productTypeToSave);

        ProductType savedProductType = productTypeRepoMock.save(productTypeToSave);

        assertEquals(productTypeToSave, savedProductType);
    }

    @Test
    void testDeleteById() {
        int productTypeId = 1;

        productTypeRepoMock.deleteById(productTypeId);

        verify(productTypeRepoMock, times(1)).deleteById(productTypeId);
    }
}
