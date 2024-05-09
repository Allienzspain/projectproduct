package com.product.purchase.service.PurchaseService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.purchase.service.PurchaseService.dto.PurchaseInfoDto;
import com.product.purchase.service.PurchaseService.models.PurchaseInfo;
import com.product.purchase.service.PurchaseService.service.ProductService;
import com.product.purchase.service.PurchaseService.service.PurchaseServiceImpl;
import com.product.purchase.service.PurchaseService.service.UserService;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseServiceImpl purchaseService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    // • Do not proceed with a purchase if there is no stock.
    @PostMapping("/process")
    public ResponseEntity<PurchaseInfo> processPurchase(
            @RequestParam int productId,
            @RequestParam int quantity,
            @RequestParam int userId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        userService.getBothProfile(jwt);
        PurchaseInfo purchase = purchaseService.processPurchase(productId, quantity, userId, jwt);

        return new ResponseEntity<>(purchase, HttpStatus.OK);
    }

    // • Allow any user to consult their completed purchases.
    @GetMapping("/purchaseHistory/{userId}")
    public ResponseEntity<List<PurchaseInfoDto>> getPurchaseHistory(@PathVariable int userId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        userService.getBothProfile(jwt);
        List<PurchaseInfoDto> purchaseHistory = purchaseService.getPurchaseHistory(userId, jwt);
        if (purchaseHistory.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(purchaseHistory);
        }
    }

    @GetMapping("/getPurchaseHistory/{userId}")
    public ResponseEntity<PurchaseInfo> getPurchaseInfo(@PathVariable int purchaseId,
            @RequestHeader("Authorization") String jwt) {
        PurchaseInfo purchaseHistory = purchaseService.getPurchaseHistoryById(purchaseId, jwt);
        if (purchaseHistory == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(purchaseHistory, HttpStatus.OK);
    }

    // • Allow any user to consult purchases made by product type.
    @GetMapping("/byProductType/{productType}")
    public ResponseEntity<List<PurchaseInfo>> getPurchasesByProductType(@PathVariable String productType,
            @RequestHeader("Authorization") String jwt) throws Exception {

        userService.getBothProfile(jwt);

        List<PurchaseInfo> purchases = purchaseService.getPurchasesByProductType(productType, jwt);
        return ResponseEntity.ok(purchases);
    }
}
