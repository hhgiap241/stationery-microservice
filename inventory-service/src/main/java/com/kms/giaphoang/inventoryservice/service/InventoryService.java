package com.kms.giaphoang.inventoryservice.service;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/29/2022, Monday
 * @project: spring-boot-stationery
 **/
public interface InventoryService {
    public Boolean isInStock(String skuCode);
}
