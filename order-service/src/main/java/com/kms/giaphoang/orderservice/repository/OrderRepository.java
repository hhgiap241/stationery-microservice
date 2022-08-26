package com.kms.giaphoang.orderservice.repository;

import com.kms.giaphoang.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: spring-boot-stationery
 **/
public interface OrderRepository extends JpaRepository<Order, Long> {
}
