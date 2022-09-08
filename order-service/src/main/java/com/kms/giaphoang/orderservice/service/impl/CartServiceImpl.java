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

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
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
            cart = Cart.builder()
                    .userId(userId)
                    .cartItems(Collections.emptyList())
                    .build();
            cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    public String addItemToCart(CartDto cartDto) {
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
            Cart cart = cartRepository.findByUserId(cartDto.getUserId()).orElse(null);
            // if cart not existed => create it with the following item
            if (cart == null) {
                cart = Cart.builder()
                        .userId(cartDto.getUserId())
                        .cartItems(cartItems)
                        .build();
            } else {
                // TODO: need to handle case add product multiple time => increase quantity and price
//                cartItems.forEach(cart::addItemToCart);
                Cart finalCart = cart;
                cartItems.forEach(item -> {
                    final List<CartItem> oldItemList = finalCart.getCartItems();
                    int index = oldItemList.indexOf(item);
                    if (index != -1) {
                        CartItem oldItem = oldItemList.get(index);
                        // update price and quantity
                        CartItem newCartItem = CartItem.builder()
                                .id(item.getId())
                                .price(item.getPrice() + oldItem.getPrice())
                                .quantity(item.getQuantity() + oldItem.getQuantity())
                                .skuCode(item.getSkuCode())
                                .build();
                        oldItemList.set(index, newCartItem);
                    } else {
                        finalCart.addItemToCart(item);
                    }
                });
            }
            return cartRepository.save(cart).getUserId();
        } else {
            throw new UpdateCartFailException("Update cart failed.");
        }
    }

    @Override
    public String removeItemFromCart(CartDto cartDto) {
        // handle for remove product has quantity = 1
        final Cart cart = cartRepository.findByUserId(cartDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found!"));
        cartDto.getCartItemList().stream()
                .map(item -> CartItem.builder()
                        .id(item.getId())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .skuCode(item.getSkuCode())
                        .build())
                .forEach(item -> {
                    final List<CartItem> oldItemList = cart.getCartItems();
                    int index = oldItemList.indexOf(item);
                    if (item.getQuantity() == 1) {
                        cart.removeItemFromCart(item);
                    } else {
                        CartItem oldItem = oldItemList.get(index);
                        // update price and quantity
                        DecimalFormat decimalFormat = new DecimalFormat("#.00");
                        CartItem newCartItem = CartItem.builder()
                                .id(item.getId())
                                .price(Double.valueOf(decimalFormat.format(oldItem.getPrice() - item.getPrice())))
                                .quantity(oldItem.getQuantity() - item.getQuantity())
                                .skuCode(item.getSkuCode())
                                .build();
                        oldItemList.set(index, newCartItem);
                    }
                });
        // TODO: problem is after remove item, it just update the cart_id in cart_item table, not delete this line => fix later
        return cartRepository.save(cart).getUserId();
    }
}
