package com.Product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Product.exception.ProductNotFoundException;
import com.Product.model.Product;
import com.Product.model.ProductType;
import com.Product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Override
    public Product createProduct(Product product) throws Exception {

        return productRepo.save(product);
    }

    @Override
    public Product getProductById(int id) throws Exception {

        return productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product not found with id: " + id));
    }

    @Override
    public List<Product> getAllProducts() {

        return this.productRepo.findAll();
    }

    @Override
    public Product updateProduct(int id, Product updatedProduct)
            throws Exception {

        Product existingProduct = this.productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product is not Found with Id: " + id));
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setType(updatedProduct.getType());

        return this.productRepo.save(existingProduct);
    }

    @Override
    public void updateProductStock(int productId, int newStock) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));

        productRepo.save(product);
    }

    @Override
    public List<Product> getAllProductByTypeName(String typeName) {

        ProductType type = new ProductType();
        type.setTypeName(typeName);

        return productRepo.findByTypeTypeName(typeName);
    }
}
