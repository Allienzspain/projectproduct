package com.product.purchase.service.PurchaseService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.purchase.service.PurchaseService.models.PurchaseInfo;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseInfo, Integer> {
    List<PurchaseInfo> findByProductId(int productId);

    List<PurchaseInfo> findByUserId(int userId);

}
