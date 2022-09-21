package com.kms.giaphoang.orderservice.controller;

import com.kms.giaphoang.orderservice.dto.CartDto;
import com.kms.giaphoang.orderservice.dto.CartItemDto;
import com.kms.giaphoang.orderservice.model.Cart;
import com.kms.giaphoang.orderservice.service.CartService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 9/8/2022, Thursday
 * @project: spring-boot-stationery-chain
 **/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class CartController extends AbstractApplicationController {
    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCartByUserId(@PathVariable String userId) {
        final Cart result = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(mapper.toCartDto(result));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateCartItem(@PathVariable String userId, @RequestBody CartItemDto cartItemDto) {
        final String result = cartService.updateCartItem(userId, cartItemDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @CircuitBreaker(name = "inventory", fallbackMethod = "addItemToCartFallback")
    public ResponseEntity<String> addToCart(@RequestBody CartDto cartDto) {
        final String result = cartService.addItemToCart(cartDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<String> removeFromCart(@RequestBody CartDto cartDto) {
        final String result = cartService.removeItemFromCart(cartDto);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<String> addItemToCartFallback(CartDto cartDto, RuntimeException runtimeException) {
        return new ResponseEntity<>("Add item to cart failed. Check inventory service status.", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
