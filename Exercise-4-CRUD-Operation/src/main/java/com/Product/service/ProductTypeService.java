package com.Product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Product.model.ProductType;

@Service
public interface ProductTypeService {

    ProductType createProductType(ProductType product) throws Exception;

    ProductType updateProductType(int id, ProductType productType) throws Exception;

    ProductType getProductTypeById(Integer id);

    List<ProductType> getAllProductTypes();

    // ProductType updateProductType(Integer id, ProductType productType);

    void deleteProductType(Integer id);

}
