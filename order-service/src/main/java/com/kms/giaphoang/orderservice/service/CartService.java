package com.kms.giaphoang.orderservice.service;

import com.kms.giaphoang.orderservice.dto.CartDto;
import com.kms.giaphoang.orderservice.model.Cart;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 9/8/2022, Thursday
 * @project: spring-boot-stationery-chain
 **/
public interface CartService {
    Cart getCartByUserId(String userId);
    String addItemToCart(CartDto cartDto);
    String removeItemFromCart(CartDto cartDto);
}
