package com.kms.giaphoang.orderservice.service.impl;

import com.kms.giaphoang.orderservice.dto.InventoryDto;
import com.kms.giaphoang.orderservice.dto.OrderDto;
import com.kms.giaphoang.orderservice.model.Order;
import com.kms.giaphoang.orderservice.model.OrderLineItems;
import com.kms.giaphoang.orderservice.repository.OrderRepository;
import com.kms.giaphoang.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
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
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderLineItems)
                .build();
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
        final boolean allProductInStock = Arrays.stream(inventoryDtos).allMatch(InventoryDto::getIsInStock);
        if (allProductInStock) {
            return orderRepository.save(order).getId().toString();
        } else {
            throw new IllegalArgumentException("Product is not available");
        }
    }
}
