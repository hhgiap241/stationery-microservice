package com.kms.giaphoang.orderservice.service.impl;

import com.kms.giaphoang.orderservice.dto.CartDto;
import com.kms.giaphoang.orderservice.dto.InventoryDto;
import com.kms.giaphoang.orderservice.exception.UpdateCartFailException;
import com.kms.giaphoang.orderservice.model.Cart;
import com.kms.giaphoang.orderservice.model.CartItem;
import com.kms.giaphoang.orderservice.repository.CartRepository;
import com.kms.giaphoang.orderservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 9/8/2022, Thursday
 * @project: spring-boot-stationery-chain
 **/
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final WebClient.Builder webClientBuilder;

    @Override
    public Cart getCartByUserId(String userId) {
        // if first time user access to cart => create cart
        Cart cart = cartRepository.findByUserId(userId).orElse(null);
        if (cart == null) {
            cart = Cart.builder().build();
            cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    public String addToCart(CartDto cartDto) {
        final List<CartItem> cartItems = cartDto.getCartItemList().stream()
                .map(cartItemDto -> CartItem.builder()
                        .price(cartItemDto.getPrice())
                        .quantity(cartItemDto.getQuantity())
                        .skuCode(cartItemDto.getSkuCode())
                        .build())
                .collect(Collectors.toList());
        final List<String> skuCodeList = cartItems.stream()
                .map(CartItem::getSkuCode)
                .collect(Collectors.toList());
        // call inventory service to check if this product is available
        final InventoryDto[] inventoryDtos = webClientBuilder.build().get()
                .uri("http://inventory-service/api/v1/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodeList).build())
                .retrieve()
                .bodyToMono(InventoryDto[].class)
                .block();
        final boolean allProductInStock = Arrays.stream(inventoryDtos).allMatch(InventoryDto::getIsInStock);
        if (allProductInStock) {
            // if cart existed just extend the item
            cartRepository.findByUserId(cartDto.getUserId())
                    .ifPresent(cart -> cart.setCartItems(cartItems)); // maybe error here
            // if cart not existed => create it with the following item
            Cart cart = Cart.builder()
                    .userId(cartDto.getUserId())
                    .cartItems(cartItems)
                    .build();
            return cartRepository.save(cart).getUserId();
        } else {
            throw new UpdateCartFailException("Update cart failed.");
        }
    }
}
