package com.kms.giaphoang.orderservice.dto;

import com.kms.giaphoang.orderservice.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: spring-boot-stationery
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
