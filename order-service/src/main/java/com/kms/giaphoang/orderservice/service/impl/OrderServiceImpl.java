package com.kms.giaphoang.orderservice.service.impl;

import com.kms.giaphoang.orderservice.dto.OrderDto;
import com.kms.giaphoang.orderservice.model.Order;
import com.kms.giaphoang.orderservice.model.OrderLineItems;
import com.kms.giaphoang.orderservice.repository.OrderRepository;
import com.kms.giaphoang.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return orderRepository.save(order).getId().toString();
    }
}
