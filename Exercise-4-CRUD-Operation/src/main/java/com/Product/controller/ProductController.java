package com.Product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.Product.dto.UserDto;
import com.Product.exception.ProductNotFoundException;
import com.Product.model.Product;
import com.Product.service.ProductService;
import com.Product.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product,
            @RequestHeader("Authorization") String jwt, HttpServletResponse response) throws Exception {
        try {
            UserDto user = userService.getAdminProfile(jwt);
            if (user == null) {
                throw new ProductNotFoundException("Sorry!! Only admin can create Product");
            }

            Product createdProduct = productService.createProduct(product);
            return ResponseEntity.ok(createdProduct);
        } catch (HttpClientErrorException.Unauthorized unauthorizedException) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProduct(@RequestHeader("Authorization") String jwt) throws Exception {
        try {
            userService.getBothProfile(jwt);
            List<Product> allProducts = this.productService.getAllProducts();
            return new ResponseEntity<>(allProducts, HttpStatus.OK);
        } catch (HttpClientErrorException.Unauthorized unauthorizedException) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("/getById/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        try {
            userService.getBothProfile(jwt);
            Product productById = this.productService.getProductById(productId);
            return new ResponseEntity<>(productById, HttpStatus.OK);
        } catch (HttpClientErrorException.Unauthorized unauthorizedException) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PutMapping("/update/{IdProduct}")
    public ResponseEntity<Product> updateProduct(@PathVariable int productId, @RequestBody Product product,
            @RequestHeader("Authorization") String jwt) throws Exception {
        try {
            userService.getAdminProfile(jwt);
            Product updateProduct = this.productService.updateProduct(productId, product);
            return new ResponseEntity<>(updateProduct, HttpStatus.OK);
        } catch (HttpClientErrorException.Unauthorized unauthorizedException) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    // • Enable an admin user to update product stock.
    @PutMapping("/updateStock/{productId}")
    public ResponseEntity<Product> updateProductStock(@PathVariable int productId, @RequestParam int newStock,
            @RequestHeader("Authorization") String jwt) {
        try {
            userService.getAdminProfile(jwt);
            productService.updateProductStock(productId, newStock);
            return ResponseEntity.ok().build();
        } catch (HttpClientErrorException.Unauthorized unauthorizedException) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("/typeName/{typeName}")
    public ResponseEntity<List<Product>> getAllProductByType(@PathVariable String typeName,
            @RequestHeader("Authorization") String jwt) {
        try {
            userService.getBothProfile(jwt);
            List<Product> allProductByType = productService.getAllProductByTypeName(typeName);
            return new ResponseEntity<>(allProductByType, HttpStatus.OK);
        } catch (HttpClientErrorException.Unauthorized unauthorizedException) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

}
