package com.kms.giaphoang.orderservice.exception;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 9/8/2022, Thursday
 * @project: spring-boot-stationery-chain
 **/
public class UpdateCartFailException extends RuntimeException{
    public UpdateCartFailException(String message) {
        super(message);
    }
}
