package com.kms.giaphoang.orderservice.controller;

import com.kms.giaphoang.orderservice.dto.CartDto;
import com.kms.giaphoang.orderservice.model.Cart;
import com.kms.giaphoang.orderservice.service.CartService;
import lombok.RequiredArgsConstructor;
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

    @PutMapping
    public ResponseEntity<String> addToCart(@RequestBody CartDto cartDto) {
        final String result = cartService.addToCart(cartDto);
        return ResponseEntity.ok(result);
    }
}
