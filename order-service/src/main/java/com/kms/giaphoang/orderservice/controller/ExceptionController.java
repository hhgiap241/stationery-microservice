package com.kms.giaphoang.orderservice.controller;

import com.kms.giaphoang.orderservice.exception.UpdateCartFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 9/8/2022, Thursday
 * @project: spring-boot-stationery-chain
 **/
@Slf4j
@RestControllerAdvice
public class ExceptionController {
    private static final String ERROR_MESSAGE = "Cart_Error: ";

    @ExceptionHandler(UpdateCartFailException.class)
    public ResponseEntity<Map<String, String>> handleUpdateCartException(UpdateCartFailException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(Map.of(ERROR_MESSAGE, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentValidationException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
