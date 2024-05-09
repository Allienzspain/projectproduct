package com.Product.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Product.dto.RoleDto;
import com.Product.dto.UserDto;
import com.Product.model.ProductType;
import com.Product.service.ProductTypeService;
import com.Product.service.UserService;

@ExtendWith(MockitoExtension.class)
public class ProductTypeControllerTest {

    @Mock
    private ProductTypeService productTypeService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ProductTypeController productTypeController;

    @Test
    public void testCreateProductType() throws Exception {
        ProductType testProductType = new ProductType();
        testProductType.setId(1);

        UserDto user = new UserDto();
        user.setRoles(Collections.singleton(new RoleDto(1, "ROLE_ADMIN")));
        when(userService.getBothProfile(anyString())).thenReturn(user);

        when(productTypeService.createProductType(any(ProductType.class))).thenReturn(testProductType);

        ResponseEntity<ProductType> response = productTypeController.createProduct(testProductType, "validJWTToken");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testProductType, response.getBody());
    }

    @Test
    public void testGetProductTypeById() throws Exception {
        UserDto user = new UserDto();
        user.setRoles(Collections.singleton(new RoleDto(1, "ROLE_ADMIN")));
        when(userService.getBothProfile(anyString())).thenReturn(user);

        ProductType productType = new ProductType();
        when(productTypeService.getProductTypeById(anyInt())).thenReturn(productType);

        ResponseEntity<ProductType> response = productTypeController.getProductTypeById(1, "validJWTToken");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productType, response.getBody());
    }

    @Test
    public void testGetProductTypeByIdNotFound() throws Exception {
        UserDto user = new UserDto();
        user.setRoles(Collections.singleton(new RoleDto(1, "ROLE_ADMIN")));
        when(userService.getBothProfile(anyString())).thenReturn(user);

        when(productTypeService.getProductTypeById(anyInt())).thenReturn(null);

        ResponseEntity<ProductType> response = productTypeController.getProductTypeById(1, "validJWTToken");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetAllProductTypes() throws Exception {
        UserDto user = new UserDto();
        user.setRoles(Collections.singleton(new RoleDto(1, "ROLE_ADMIN")));
        when(userService.getBothProfile(anyString())).thenReturn(user);

        List<ProductType> productTypes = Collections.singletonList(new ProductType());
        when(productTypeService.getAllProductTypes()).thenReturn(productTypes);

        ResponseEntity<List<ProductType>> response = productTypeController.getAllProductTypes("validJWTToken");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productTypes, response.getBody());
    }

    @Test
    public void testUpdateProductType() throws Exception {
        UserDto adminUser = new UserDto();
        adminUser.setRoles(Collections.singleton(new RoleDto(1, "ROLE_ADMIN")));
        when(userService.getAdminProfile(anyString())).thenReturn(adminUser);

        ProductType updatedProductType = new ProductType();
        when(productTypeService.updateProductType(anyInt(), any(ProductType.class))).thenReturn(updatedProductType);

        ResponseEntity<ProductType> response = productTypeController.updateProduct(1, new ProductType(),
                "validJWTToken");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProductType, response.getBody());
    }

    @Test
    public void testDeleteProductType() throws Exception {
        UserDto adminUser = new UserDto();
        adminUser.setRoles(Collections.singleton(new RoleDto(1, "ROLE_ADMIN")));
        when(userService.getAdminProfile(anyString())).thenReturn(adminUser);

        ResponseEntity<Void> response = productTypeController.deleteProductType(1, "validJWTToken");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testauthorizedAccessNotFound() throws Exception {
        UserDto user = new UserDto();
        user.setRoles(Collections.singleton(new RoleDto(1, "ROLE_USER")));
        when(userService.getBothProfile(anyString())).thenReturn(user);

        ResponseEntity<ProductType> response = productTypeController.getProductTypeById(1, "validJWTToken");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

}
