package com.Product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Product.exception.ProductNotFoundException;
import com.Product.model.ProductType;
import com.Product.repository.ProductTypeRepo;

@Service
public class ProductTypeServiceType implements ProductTypeService {
    @Autowired
    private ProductTypeRepo productTypeRepo;

    @Override
    public ProductType getProductTypeById(Integer id) {

        return productTypeRepo.findById(id).orElse(null);
    }

    @Override
    public List<ProductType> getAllProductTypes() {

        return productTypeRepo.findAll();
    }

    @Override
    public void deleteProductType(Integer id) {

        productTypeRepo.deleteById(id);
    }

    @Override
    public ProductType updateProductType(int id, ProductType updateProductType)
            throws Exception {

        ProductType existingProduct = productTypeRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        existingProduct.setTypeName(updateProductType.getTypeName());

        return productTypeRepo.save(existingProduct);
    }

    @Override
    public ProductType createProductType(ProductType product) throws Exception {

        return productTypeRepo.save(product);
    }

    // private void checkAdminPermission(Set<RoleDto> requesterRoles) throws
    // ProductNotFoundException {
    // boolean isAdmin = requesterRoles.stream().anyMatch(role ->
    // role.getRoleName().equals("ROLE_ADMIN"));
    // if (!isAdmin) {
    // throw new ProductNotFoundException("Sorry!! Only admin can create Product");
    // }
    // }

    // private void checkAdminOrUserPermission(Set<RoleDto> requesterRoles) throws
    // ProductNotFoundException {
    // boolean isAdmin = requesterRoles.stream().anyMatch(role ->
    // role.getRoleName().equals("ROLE_ADMIN"));
    // boolean isUser = requesterRoles.stream().anyMatch(role ->
    // role.getRoleName().equals("ROLE_USER"));

    // if (!isAdmin && !isUser) {
    // throw new ProductNotFoundException("Sorry!! Only admin or user can create
    // Product");
    // }
    // }

    // private void checkUserPermission(Set<RoleDto> requesterRoles) throws
    // ProductNotFoundException {
    // boolean isUser = requesterRoles.stream().anyMatch(role ->
    // role.getRoleName().equals("ROLE_USER"));
    // if (!isUser) {
    // throw new ProductNotFoundException("Sorry!! Only user can create Product");
    // }
    // }

}
