package com.kms.giaphoang.inventoryservice.service.impl;

import com.kms.giaphoang.inventoryservice.dto.InventoryDto;
import com.kms.giaphoang.inventoryservice.exception.InventoryExistedException;
import com.kms.giaphoang.inventoryservice.exception.InventoryNotFoundException;
import com.kms.giaphoang.inventoryservice.model.Inventory;
import com.kms.giaphoang.inventoryservice.repository.InventoryRepository;
import com.kms.giaphoang.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/29/2022, Monday
 * @project: spring-boot-stationery
 **/
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    @Override
    public List<Inventory> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode);
    }
    @Override
    public String saveInventory(InventoryDto inventoryDto) {
        inventoryRepository.findBySkuCode(inventoryDto.getSkuCode())
                .ifPresent(inventory -> new InventoryExistedException("Inventory with skuCode " + inventoryDto.getSkuCode() + " existed"));
        Inventory inventory = Inventory.builder()
                .skuCode(inventoryDto.getSkuCode())
                .quantity(inventoryDto.getQuantity())
                .build();
        return inventoryRepository.save(inventory).getId().toString();
    }

    @Override
    public String updateInventory(InventoryDto inventoryDto) {
        final Inventory inventory = inventoryRepository.findBySkuCode(inventoryDto.getSkuCode())
                .orElseThrow(()-> new InventoryNotFoundException("Inventory not found"));
        inventory.setQuantity(inventoryDto.getQuantity());
        return inventoryRepository.save(inventory).getId().toString();
    }

    @Override
    public Inventory getInventoryBySkuCode(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found"));
    }
}
