package com.kms.giaphoang.productservice.controller;

import com.kms.giaphoang.productservice.exception.CategoryNotFoundException;
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
 * @created : 8/26/2022, Friday
 * @project: product-service
 **/
@Slf4j
@RestControllerAdvice
public class ExceptionController {
    private static final String ERROR_MESSAGE = "Error: ";

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCategoryNotFoundException(CategoryNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(Map.of(ERROR_MESSAGE, e.getMessage()));
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<Map<String, String>> handleProductNotFoundException(ProductNotFoundException e) {
//        log.error(e.getMessage());
//        return ResponseEntity.badRequest().body(Map.of(ERROR_MESSAGE, e.getMessage()));
//    }

//    @ExceptionHandler(CategoryExistedException.class)
//    public ResponseEntity<Map<String, String>> handleCategoryExistedException(CategoryExistedException e) {
//        log.error(e.getMessage());
//        return ResponseEntity.badRequest().body(Map.of(ERROR_MESSAGE, e.getMessage()));
//    }


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

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException e) {
//        Map<String, String> errors = new HashMap<>();
//        e.getConstraintViolations().forEach(error -> {
//            String fieldName = error.getPropertyPath().toString();
//            String errorMessage = error.getMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return ResponseEntity.badRequest().body(errors);
//    }
}
