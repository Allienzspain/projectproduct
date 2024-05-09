package com.product.purchase.service.PurchaseService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.purchase.service.PurchaseService.dto.PurchaseInfoDto;
import com.product.purchase.service.PurchaseService.models.PurchaseInfo;

@Service
public interface PurchaseService {

    PurchaseInfo processPurchase(int productId, int quantity, int userId, String jwt)
            throws Exception;

    PurchaseInfo getPurchaseHistoryById(int purchaseId, String jwt);

    List<PurchaseInfoDto> getPurchaseHistory(int userId, String jwt) throws Exception;

    public List<PurchaseInfo> getPurchasesByProductType(String productType, String jwt) throws Exception;

}
