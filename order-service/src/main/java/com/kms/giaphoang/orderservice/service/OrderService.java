package com.kms.giaphoang.orderservice.service;

import com.kms.giaphoang.orderservice.dto.OrderDto;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: spring-boot-stationery
 **/
public interface OrderService {
    public String placeOrder(OrderDto orderDto);
}
