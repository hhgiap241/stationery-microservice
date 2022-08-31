package com.kms.giaphoang.productservice.exception;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/31/2022, Wednesday
 * @project: spring-boot-stationery-chain
 **/
public class CategoryExistedException extends RuntimeException {
    public CategoryExistedException(String message) {
        super(message);
    }
}
