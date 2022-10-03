package com.kms.giaphoang.orderservice.exception;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 10/3/2022, Monday
 * @project: spring-boot-stationery-chain
 **/
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
