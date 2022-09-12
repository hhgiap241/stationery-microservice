package com.kms.giaphoang.orderservice.util;

import com.kms.giaphoang.orderservice.dto.CartDto;
import com.kms.giaphoang.orderservice.dto.CartItemDto;
import com.kms.giaphoang.orderservice.dto.OrderDto;
import com.kms.giaphoang.orderservice.dto.OrderLineItemsDto;
import com.kms.giaphoang.orderservice.model.Cart;
import com.kms.giaphoang.orderservice.model.CartItem;
import com.kms.giaphoang.orderservice.model.Order;
import com.kms.giaphoang.orderservice.model.OrderLineItems;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: spring-boot-stationery
 **/
@Component
public class ApplicationMapper {
    public OrderLineItemsDto toOrderLineItemsDto(OrderLineItems orderLineItems) {
        return OrderLineItemsDto.builder()
                .id(orderLineItems.getId())
                .price(orderLineItems.getPrice())
                .skuCode(orderLineItems.getSkuCode())
                .quantity(orderLineItems.getQuantity())
                .build();
    }

    public CartItemDto toCartItemDto(CartItem cartItem) {
        return CartItemDto.builder().id(cartItem.getId())
                .price(cartItem.getPrice())
                .skuCode(cartItem.getSkuCode())
                .quantity(cartItem.getQuantity())
                .build();
    }

    public OrderDto toOrderDto(Order order) {
        return OrderDto.builder()
                .orderLineItemsDtoList(order.getOrderLineItemsList().stream()
                        .map(this::toOrderLineItemsDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public CartDto toCartDto(Cart cart) {
        return CartDto.builder()
                .userId(cart.getUserId())
                .cartItemList(cart.getCartItems().stream()
                        .map(this::toCartItemDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
