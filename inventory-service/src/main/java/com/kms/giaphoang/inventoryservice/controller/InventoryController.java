package com.kms.giaphoang.inventoryservice.controller;

import com.kms.giaphoang.inventoryservice.dto.InventoryDto;
import com.kms.giaphoang.inventoryservice.model.Inventory;
import com.kms.giaphoang.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/29/2022, Monday
 * @project: spring-boot-stationery
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventoryController extends AbstractApplicationController {
    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryDto>> isInStock(@RequestParam List<String> skuCode) {
        final List<Inventory> inventoryList = inventoryService.isInStock(skuCode);
        return ResponseEntity.ok(inventoryList.stream()
                .map(mapper::toInventoryDto)
                .collect(Collectors.toList()));
    }
    @PostMapping
    public ResponseEntity<String> saveInventory(@RequestBody InventoryDto inventoryDto){
        return ResponseEntity.ok(inventoryService.saveInventory(inventoryDto));
    }
}
