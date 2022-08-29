package com.kms.giaphoang.inventoryservice.util;

import com.kms.giaphoang.inventoryservice.dto.InventoryDto;
import com.kms.giaphoang.inventoryservice.model.Inventory;
import org.springframework.stereotype.Component;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/29/2022, Monday
 * @project: spring-boot-stationery-chain
 **/
@Component
public class ApplicationMapper {
    public InventoryDto toInventoryDto(Inventory inventory){
        return InventoryDto.builder()
                .id(inventory.getId())
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity() > 0)
                .quantity(inventory.getQuantity())
                .build();
    }
}
