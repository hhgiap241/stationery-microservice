package com.kms.giaphoang.orderservice.service;

import com.kms.giaphoang.orderservice.dto.OrderDto;
import com.kms.giaphoang.orderservice.model.Order;
import com.kms.giaphoang.orderservice.model.enums.OrderStatus;

import java.util.List;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: spring-boot-stationery
 **/
public interface OrderService {
    public String placeOrder(OrderDto orderDto);
    public String updateOrderStatus(Long orderId, OrderStatus status);
    public List<Order> getOrdersByUserId(String userId);
    public Order getOrderById(Long orderId);
    public List<Order> getAllOrders();
}
