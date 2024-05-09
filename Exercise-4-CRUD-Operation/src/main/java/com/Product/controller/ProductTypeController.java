package com.Product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.Product.exception.ProductNotFoundException;
import com.Product.model.ProductType;
import com.Product.service.ProductTypeService;
import com.Product.service.UserService;

@RestController
@RequestMapping("/type")
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private UserService userService;

    @PostMapping("/createType")
    public ResponseEntity<ProductType> createProduct(@RequestBody ProductType product,
            @RequestHeader("Authorization") String jwt) throws Exception {
        try {
            userService.getUserProfile(jwt);

            ProductType createdProduct = productTypeService.createProductType(product);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (HttpClientErrorException.Unauthorized unauthorizedException) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ProductType> getProductTypeById(@PathVariable int id,
            @RequestHeader("Authorization") String jwt) throws Exception {
        try {
            userService.getBothProfile(jwt);
            ProductType productTypeById = this.productTypeService.getProductTypeById(id);
            if (productTypeById != null) {
                return new ResponseEntity<>(productTypeById, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (HttpClientErrorException.Unauthorized unauthorizedException) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

    }

    // • Allow any user to consult purchases made by product type.
    @GetMapping("/getAllProductTypes")
    public ResponseEntity<List<ProductType>> getAllProductTypes(@RequestHeader("Authorization") String jwt) {
        try {
            userService.getBothProfile(jwt);
            List<ProductType> productTypes = productTypeService.getAllProductTypes();
            return new ResponseEntity<>(productTypes, HttpStatus.OK);
        } catch (HttpClientErrorException.Unauthorized unauthorizedException) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

    }

    // • Enable an admin user to add product types.
    @PutMapping("/updateType/{id}")
    public ResponseEntity<ProductType> updateProduct(@PathVariable int id,
            @RequestBody ProductType productType,
            @RequestHeader("Authorization") String jwt) throws Exception {
        userService.getAdminProfile(jwt);

        try {
            ProductType updatedProduct = productTypeService.updateProductType(id, productType);
            return ResponseEntity.ok(updatedProduct);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (HttpClientErrorException.Unauthorized unauthorizedException) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProductType(@PathVariable Integer id,
            @RequestHeader("Authorization") String jwt) {
        try {
            userService.getAdminProfile(jwt);
            productTypeService.deleteProductType(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (HttpClientErrorException.Unauthorized unauthorizedException) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

}
