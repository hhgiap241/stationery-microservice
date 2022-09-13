package com.kms.giaphoang.inventoryservice.service;

import com.kms.giaphoang.inventoryservice.dto.InventoryDto;
import com.kms.giaphoang.inventoryservice.dto.OrderLineItemsDto;
import com.kms.giaphoang.inventoryservice.model.Inventory;

import java.util.List;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/29/2022, Monday
 * @project: spring-boot-stationery
 **/
public interface InventoryService {
    public List<Inventory> getAllInventory();
    public List<Inventory> isInStock(List<String> skuCode);
    public String saveInventory(InventoryDto inventoryDto);
    String updateInventory(InventoryDto inventoryDto);
    void updateAllInventory(List<OrderLineItemsDto> orderLineItemsDtoList);
    Inventory getInventoryBySkuCode(String skuCode);
}
