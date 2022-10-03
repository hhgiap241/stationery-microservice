package com.kms.giaphoang.orderservice.service.impl;

import com.kms.giaphoang.orderservice.dto.InventoryDto;
import com.kms.giaphoang.orderservice.dto.OrderDto;
import com.kms.giaphoang.orderservice.exception.OrderNotFoundException;
import com.kms.giaphoang.orderservice.exception.UpdateCartFailException;
import com.kms.giaphoang.orderservice.model.Cart;
import com.kms.giaphoang.orderservice.model.Order;
import com.kms.giaphoang.orderservice.model.OrderLineItems;
import com.kms.giaphoang.orderservice.model.enums.OrderStatus;
import com.kms.giaphoang.orderservice.repository.CartRepository;
import com.kms.giaphoang.orderservice.repository.OrderRepository;
import com.kms.giaphoang.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: spring-boot-stationery
 **/
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final WebClient.Builder webClientBuilder;

    @Override
    public String placeOrder(OrderDto orderDto) {
        final List<OrderLineItems> orderLineItems = orderDto.getOrderLineItemsDtoList().stream()
                .map(orderLineItemsDto -> OrderLineItems.builder()
                        .price(orderLineItemsDto.getPrice())
                        .quantity(orderLineItemsDto.getQuantity())
                        .skuCode(orderLineItemsDto.getSkuCode())
                        .build())
                .collect(Collectors.toList());
        Order order = Order.builder()
                .userId(orderDto.getUserId())
                .customerName(orderDto.getCustomerName())
                .customerAddress(orderDto.getCustomerAddress())
                .customerPhone(orderDto.getCustomerPhone())
                .totalPrice(orderDto.getTotalPrice())
                .orderStatus(orderDto.getOrderStatus())
                .orderLineItemsList(orderLineItems)
                .build();
        // get skuCode list
        final List<String> skuCodeList = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .collect(Collectors.toList());
        // call inventory service to check if this product is available
        final InventoryDto[] inventoryDtos = webClientBuilder.build().get()
                .uri("http://inventory-service/api/v1/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodeList).build())
                .retrieve()
                .bodyToMono(InventoryDto[].class)
                .block();
        // call api to update inventory
        final String block = webClientBuilder.build().put()
                .uri("http://inventory-service/api/v1/inventory")
                .bodyValue(orderDto.getOrderLineItemsDtoList())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(block);
        final boolean allProductInStock = Arrays.stream(inventoryDtos).allMatch(InventoryDto::getIsInStock);
        if (allProductInStock) {
            final String orderId = orderRepository.save(order).getId().toString();
            // update cart
            final Cart cart = cartRepository.findByUserId(orderDto.getUserId())
                    .orElseThrow(() -> new UpdateCartFailException("Update cart fail"));
            cart.setCartItems(Collections.emptyList());
            cartRepository.save(cart);
            return orderId;
        } else {
            throw new IllegalArgumentException("Create order failed. Some products is not available");
        }
    }

    @Override
    public String updateOrderStatus(Long orderId, OrderStatus status) {
        final Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        order.setOrderStatus(status);
        return orderRepository.save(order).getId().toString();
    }
}
