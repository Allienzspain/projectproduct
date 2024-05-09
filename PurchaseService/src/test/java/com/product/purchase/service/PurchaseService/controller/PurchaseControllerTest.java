package com.product.purchase.service.PurchaseService.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.product.purchase.service.PurchaseService.models.PurchaseInfo;
import com.product.purchase.service.PurchaseService.models.UserDto;
import com.product.purchase.service.PurchaseService.service.ProductService;
import com.product.purchase.service.PurchaseService.service.PurchaseServiceImpl;
import com.product.purchase.service.PurchaseService.service.UserService;

@ExtendWith(MockitoExtension.class)
class PurchaseControllerTest {

    @Mock
    private PurchaseServiceImpl purchaseServiceMock;

    @Mock
    private UserService userServiceMock;

    @Mock
    private ProductService productServiceMock;

    @InjectMocks
    private PurchaseController purchaseController;

    @Test
    void testProcessPurchase() throws Exception {
        int productId = 1;
        int quantity = 2;
        int userId = 1;
        String jwt = "test-jwt";

        UserDto user = new UserDto();

        when(userServiceMock.getUserProfile(jwt)).thenReturn(user);

        PurchaseInfo purchaseInfo = new PurchaseInfo();
        when(purchaseServiceMock.processPurchase(productId, quantity, userId, jwt))
                .thenReturn(purchaseInfo);

        ResponseEntity<PurchaseInfo> response = purchaseController.processPurchase(productId, quantity, userId, jwt);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(purchaseInfo, response.getBody());
    }

    @Test
    void testGetAllPurchaseHistory() {
        int purchaseId = 1;
        String jwt = "test-jwt";

        PurchaseInfo purchaseInfo = new PurchaseInfo();
        when(purchaseServiceMock.getPurchaseHistoryById(purchaseId, jwt)).thenReturn(purchaseInfo);

        ResponseEntity<PurchaseInfo> response = purchaseController.getPurchaseInfo(purchaseId, jwt);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(purchaseInfo, response.getBody());
    }

    @Test
    void testGetPurchasesByProductType() throws Exception {
        String productType = "testProductType";
        String jwt = "test-jwt";

        List<PurchaseInfo> purchases = new ArrayList<>();
        when(purchaseServiceMock.getPurchasesByProductType(productType, jwt)).thenReturn(purchases);

        ResponseEntity<List<PurchaseInfo>> response = purchaseController.getPurchasesByProductType(productType, jwt);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(purchases, response.getBody());
    }
}
