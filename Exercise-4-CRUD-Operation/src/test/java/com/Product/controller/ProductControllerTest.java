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
import org.springframework.web.client.HttpClientErrorException;

import com.Product.dto.RoleDto;
import com.Product.dto.UserDto;
import com.Product.model.Product;
import com.Product.service.ProductService;
import com.Product.service.UserService;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void testAuthorizedAccess() throws Exception {
        UserDto user = new UserDto();
        user.setRoles(Collections.singleton(new RoleDto(1, "ROLE_USER")));
        when(userService.getAdminProfile(anyString())).thenReturn(user);

        ResponseEntity<Product> response = productController.updateProduct(1, new Product(), "validJWTToken");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testInvalidJwtToken() throws Exception {

        when(userService.getAdminProfile(anyString())).thenThrow(HttpClientErrorException.Unauthorized.class);

        ResponseEntity<Product> response = productController.updateProduct(1, new Product(), "invalidJWTToken");

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateProduct() throws Exception {
        UserDto adminUser = new UserDto();
        adminUser.setRoles(Collections.singleton(new RoleDto(1, "ROLE_ADMIN")));
        when(userService.getAdminProfile(anyString())).thenReturn(adminUser);

        Product testProduct = new Product();
        testProduct.setName("Test Product");
        testProduct.setDescription("Description");
        when(productService.createProduct(any(Product.class))).thenReturn(testProduct);

        ResponseEntity<Product> response = productController.createProduct(testProduct, "validJWTToken", null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testProduct, response.getBody());
    }

    @Test
    public void testCreateProductUserNotAdmin() throws Exception {

        UserDto user = new UserDto();
        user.setRoles(Collections.singleton(new RoleDto(1, "ROLE_USER")));
        when(userService.getAdminProfile(anyString())).thenReturn(user);

        ResponseEntity<Product> response = productController.createProduct(new Product(), "validJWTToken", null);

        assertNotEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetAllProduct() throws Exception {

        UserDto user = new UserDto();
        user.setRoles(Collections.singleton(new RoleDto(1, "ROLE_ADMIN")));
        when(userService.getBothProfile(anyString())).thenReturn(user);

        List<Product> productList = Collections.singletonList(new Product());
        when(productService.getAllProducts()).thenReturn(productList);

        ResponseEntity<List<Product>> response = productController.getAllProduct("validJWTToken");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
    }

    @Test
    public void testGetProductByIdNotFound() throws Exception {

        UserDto user = new UserDto();
        user.setRoles(Collections.singleton(new RoleDto(1, "ROLE_ADMIN")));
        when(userService.getBothProfile(anyString())).thenReturn(user);

        when(productService.getProductById(anyInt())).thenReturn(null);

        ResponseEntity<Product> response = productController.getProductById(1, "validJWTToken");

        assertNull(response.getBody());
    }

    @Test
    public void testGetProductById() throws Exception {

        UserDto user = new UserDto();
        user.setRoles(Collections.singleton(new RoleDto(1, "ROLE_ADMIN")));
        when(userService.getBothProfile(anyString())).thenReturn(user);

        Product product = new Product();
        when(productService.getProductById(1)).thenReturn(product);

        ResponseEntity<Product> response = productController.getProductById(1, "validJWTToken");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testUpdateProduct() throws Exception {

        UserDto adminUser = new UserDto();
        adminUser.setRoles(Collections.singleton(new RoleDto(1, "ROLE_ADMIN")));
        when(userService.getAdminProfile(anyString())).thenReturn(adminUser);

        Product updatedProduct = new Product();
        when(productService.updateProduct(anyInt(), any(Product.class))).thenReturn(updatedProduct);

        ResponseEntity<Product> response = productController.updateProduct(1, new Product(), "validJWTToken");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProduct, response.getBody());
    }

    @Test
    public void testUpdateProductStock() throws Exception {
        UserDto adminUser = new UserDto();
        adminUser.setRoles(Collections.singleton(new RoleDto(1, "ROLE_ADMIN")));
        when(userService.getAdminProfile(anyString())).thenReturn(adminUser);

        ResponseEntity<Product> response = productController.updateProductStock(1, 10, "validJWTToken");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

}
