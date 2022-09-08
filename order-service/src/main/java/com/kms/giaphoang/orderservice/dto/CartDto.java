package com.kms.giaphoang.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 9/8/2022, Thursday
 * @project: spring-boot-stationery-chain
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private String userId;
    private List<CartItemDto> cartItemList;
}
