package com.product.purchase.service.PurchaseService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class PurchaseInfoDto {
    private int purchaseId;
    private int productId;
    private int userId;
    private int quantity;
    private String productName;
    private String productDescription;
    private String productPrice;

    private int productStock;
}
