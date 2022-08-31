package com.kms.giaphoang.inventoryservice.repository;

import com.kms.giaphoang.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/29/2022, Monday
 * @project: spring-boot-stationery
 **/
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
    Optional<Inventory> findBySkuCode(String skuCode);
}
