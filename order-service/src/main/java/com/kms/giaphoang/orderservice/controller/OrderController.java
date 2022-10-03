package com.kms.giaphoang.orderservice.controller;

import com.kms.giaphoang.orderservice.dto.OrderDto;
import com.kms.giaphoang.orderservice.model.enums.OrderStatus;
import com.kms.giaphoang.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: spring-boot-stationery
 **/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderDto orderDto){
        return ResponseEntity.ok(orderService.placeOrder(orderDto));
    }
    @PutMapping("/{orderId}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status){
        final OrderStatus orderStatus = OrderStatus.valueOf(status);
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, orderStatus));
    }
}
