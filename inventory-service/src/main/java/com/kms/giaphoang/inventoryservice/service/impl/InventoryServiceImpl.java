package com.kms.giaphoang.inventoryservice.service.impl;

import com.kms.giaphoang.inventoryservice.dto.InventoryDto;
import com.kms.giaphoang.inventoryservice.model.Inventory;
import com.kms.giaphoang.inventoryservice.repository.InventoryRepository;
import com.kms.giaphoang.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Inventory inventory = Inventory.builder()
                .skuCode(inventoryDto.getSkuCode())
                .quantity(inventoryDto.getQuantity())
                .build();
        return inventoryRepository.save(inventory).getId().toString();
    }
}
