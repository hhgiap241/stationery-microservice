package com.kms.giaphoang.inventoryservice.exception;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/31/2022, Wednesday
 * @project: spring-boot-stationery-chain
 **/
public class InventoryExistedException extends RuntimeException {
    public InventoryExistedException(String message) {
        super(message);
    }
}
