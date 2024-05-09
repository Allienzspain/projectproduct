package com.product.purchase.service.PurchaseService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.purchase.service.PurchaseService.dto.ProductDto;
import com.product.purchase.service.PurchaseService.dto.PurchaseInfoDto;
import com.product.purchase.service.PurchaseService.exceptions.PurchaseNotFoundException;
import com.product.purchase.service.PurchaseService.models.PurchaseInfo;
import com.product.purchase.service.PurchaseService.repository.PurchaseRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductService productService;

    @Override
    public PurchaseInfo processPurchase(int productId, int quantity, int userId, String jwt)
            throws Exception {
        return createAndSavePurchase(productId, quantity, userId, jwt);
    }

    private PurchaseInfo createAndSavePurchase(int productId, int quantity, int userId, String jwt) throws Exception {
        ProductDto product = productService.getProductById(productId, jwt);

        if (product != null) {
            if (product.getStock() >= quantity) {
                PurchaseInfo purchaseInfo = new PurchaseInfo();
                purchaseInfo.setProductId(productId);
                purchaseInfo.setUserId(userId);
                purchaseInfo.setQuantity(quantity);
                purchaseInfo.setProductName(product.getName());
                purchaseInfo.setProductDescription(product.getDescription());
                purchaseInfo.setProductPrice(product.getPrice());
                purchaseInfo = purchaseRepository.save(purchaseInfo);

                int remainingStock = product.getStock() - quantity;
                productService.updateProductStock(productId, remainingStock, jwt);

                return purchaseInfo;
            } else {
                throw new PurchaseNotFoundException("Product not available or insufficient stock");
            }
        } else {
            throw new PurchaseNotFoundException("Product not found with ID: " + productId);
        }
    }

    @Override
    public List<PurchaseInfoDto> getPurchaseHistory(int userId, String jwt) throws Exception {
        List<PurchaseInfo> purchaseInfos = purchaseRepository.findByUserId(userId);
        List<PurchaseInfoDto> purchaseInfoDtos = new ArrayList<>();

        for (PurchaseInfo purchaseInfo : purchaseInfos) {
            PurchaseInfoDto purchaseInfoDto = new PurchaseInfoDto();
            purchaseInfoDto.setPurchaseId(purchaseInfo.getPurchaseId());
            purchaseInfoDto.setProductId(purchaseInfo.getProductId());
            purchaseInfoDto.setUserId(purchaseInfo.getUserId());
            purchaseInfoDto.setQuantity(purchaseInfo.getQuantity());

            ProductDto productDto = productService.getProductById(purchaseInfo.getProductId(), jwt);
            if (productDto != null) {
                purchaseInfoDto.setProductName(productDto.getName());
                purchaseInfoDto.setProductDescription(productDto.getDescription());
                purchaseInfoDto.setProductPrice(productDto.getPrice());
                purchaseInfoDto.setProductStock(productDto.getStock());
                purchaseInfoDto.setProductId(productDto.getIdProduct());
            }

            purchaseInfoDtos.add(purchaseInfoDto);
        }

        return purchaseInfoDtos;
    }

    @Override
    public PurchaseInfo getPurchaseHistoryById(int purchaseId, String jwt) {
        throw new UnsupportedOperationException("Unimplemented method 'getPurchaseHistoryById'");
    }

    @Override
    public List<PurchaseInfo> getPurchasesByProductType(String productType, String jwt) throws Exception {
        List<PurchaseInfo> purchaseInfos = purchaseRepository.findAll();

        productService.getPurchasesByProductType(productType, jwt);

        return purchaseInfos;
    }

}