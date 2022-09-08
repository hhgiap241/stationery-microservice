package com.kms.giaphoang.orderservice.repository;

import com.kms.giaphoang.orderservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 9/8/2022, Thursday
 * @project: spring-boot-stationery-chain
 **/
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(String userId);
}
