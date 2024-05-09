package com.product.purchase.service.PurchaseService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.product.purchase.service.PurchaseService.dto.ProductDto;

@Service
public class ProductService {

    private static final String PRODUCT_SERVICE_URL = "http://localhost:8087";

    @Autowired
    private RestTemplate restTemplate;

    public ProductDto getProductById(int id, String jwt) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwt);
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<ProductDto> responseEntity = restTemplate.exchange(
                PRODUCT_SERVICE_URL + "/product/getById/" + id,
                HttpMethod.GET,
                requestEntity,
                ProductDto.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Failed to fetch product. Status code: " + responseEntity.getStatusCodeValue());
        }
    }

    public ProductDto updateProductStock(int id, int newStock, String jwt) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwt);
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        final String URL = PRODUCT_SERVICE_URL + "/product/updateStock/" + id + "?newStock=" + newStock;
        ResponseEntity<ProductDto> responseEntity = restTemplate.exchange(
                URL,
                HttpMethod.PUT,
                requestEntity,
                ProductDto.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException(
                    "Failed to update product stock. Status code: " + responseEntity.getStatusCodeValue());
        }
    }

    public List<ProductDto> getPurchasesByProductType(String typeName, String jwt) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwt);
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<ProductDto>> responseEntity = restTemplate.exchange(
                PRODUCT_SERVICE_URL + "/product/typeName/" + typeName,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<ProductDto>>() {
                });

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Failed to fetch products. Status code: " + responseEntity.getStatusCodeValue());
        }
    }

}
