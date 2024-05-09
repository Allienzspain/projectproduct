package com.product.purchase.service.PurchaseService.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.product.purchase.service.PurchaseService.models.PurchaseInfo;
import com.product.purchase.service.PurchaseService.service.PurchaseServiceImpl;

@ExtendWith(MockitoExtension.class)
class PurchaseRepositoryTest {

    @Mock
    private PurchaseRepository purchaseRepositoryMock;

    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    @Test
    void testFindByProductId_Success() {
        int productId = 1;
        List<PurchaseInfo> expectedPurchases = List.of(new PurchaseInfo(), new PurchaseInfo());

        when(purchaseRepositoryMock.findByProductId(productId)).thenReturn(expectedPurchases);

        List<PurchaseInfo> result = purchaseRepositoryMock.findByProductId(productId);

        assertEquals(expectedPurchases.size(), result.size());
    }

    @Test
    void testFindByUserId_Success() {
        int userId = 1;
        List<PurchaseInfo> expectedPurchases = List.of(new PurchaseInfo(), new PurchaseInfo());

        when(purchaseRepositoryMock.findByUserId(userId)).thenReturn(expectedPurchases);

        List<PurchaseInfo> result = purchaseRepositoryMock.findByUserId(userId);

        assertEquals(expectedPurchases.size(), result.size());
    }
}
