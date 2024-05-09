package com.product.purchase.service.PurchaseService.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.product.purchase.service.PurchaseService.dto.ProductDto;
import com.product.purchase.service.PurchaseService.dto.PurchaseInfoDto;
import com.product.purchase.service.PurchaseService.exceptions.PurchaseNotFoundException;
import com.product.purchase.service.PurchaseService.models.PurchaseInfo;
import com.product.purchase.service.PurchaseService.models.RoleDto;
import com.product.purchase.service.PurchaseService.repository.PurchaseRepository;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceImplTest {

    @Mock
    private PurchaseRepository purchaseRepositoryMock;

    @Mock
    private ProductService productServiceMock;

    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    @Test
    void testProcessPurchase_EnoughStock_Success() throws Exception {
        int productId = 1;
        int quantity = 2;
        int userId = 2;
        Set<RoleDto> requesterRole = Collections.singleton(new RoleDto());
        String jwt = "test-jwt";

        ProductDto product = new ProductDto();
        product.setIdProduct(productId);
        product.setStock(quantity);

        when(productServiceMock.getProductById(productId, jwt)).thenReturn(product);
        when(purchaseRepositoryMock.save(any(PurchaseInfo.class))).thenReturn(new PurchaseInfo());

        PurchaseInfo result = purchaseService.processPurchase(productId, quantity, userId, jwt);

        assertNotNull(result);
        assertNotEquals(productId, result.getProductId());
        assertNotEquals(userId, result.getUserId());
    }

    @Test
    void testProcessPurchase_InsufficientStock_ExceptionThrown() throws Exception {
        int productId = 1;
        int quantity = 2;
        int userId = 1;
        Set<RoleDto> requesterRole = Collections.singleton(new RoleDto());
        String jwt = "test-jwt";

        ProductDto product = new ProductDto();
        product.setIdProduct(productId);
        product.setStock(quantity - 1);

        when(productServiceMock.getProductById(productId, jwt)).thenReturn(product);

        assertThrows(PurchaseNotFoundException.class,
                () -> purchaseService.processPurchase(productId, quantity, userId, jwt));
    }

    @Test
    void testGetPurchaseHistory_Success() throws Exception {
        int userId = 1;
        String jwt = "test-jwt";
        List<PurchaseInfo> purchaseInfos = List.of(new PurchaseInfo(), new PurchaseInfo());

        when(purchaseRepositoryMock.findByUserId(userId)).thenReturn(purchaseInfos);
        when(productServiceMock.getProductById(anyInt(), eq(jwt))).thenReturn(new ProductDto());

        List<PurchaseInfoDto> result = purchaseService.getPurchaseHistory(userId, jwt);

        assertNotNull(result);
        assertEquals(purchaseInfos.size(), result.size());
    }

    @Test
    void testGetPurchaseHistoryById_NotImplemented_ExceptionThrown() {
        int purchaseId = 1;
        String jwt = "test-jwt";

        assertThrows(UnsupportedOperationException.class,
                () -> purchaseService.getPurchaseHistoryById(purchaseId, jwt));
    }

    @Test
    void testGetPurchasesByProductType_Success() throws Exception {
        String productType = "TestType";
        String jwt = "test-jwt";
        List<PurchaseInfo> purchaseInfos = List.of(new PurchaseInfo(), new PurchaseInfo());

        when(purchaseRepositoryMock.findAll()).thenReturn(purchaseInfos);
        when(productServiceMock.getPurchasesByProductType(eq(productType), eq(jwt))).thenReturn(List.of());

        List<PurchaseInfo> result = purchaseService.getPurchasesByProductType(productType, jwt);

        assertNotNull(result);
        assertEquals(purchaseInfos.size(), result.size());
    }

}