package com.kms.giaphoang.orderservice.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: spring-boot-stationery
 **/
@Entity
@Table(name="t_order_line_item")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems {
    @Id
    @SequenceGenerator(name = "order_line_item_seq", sequenceName = "order_line_item_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_line_item_seq")
    private Long id;
    private String skuCode;
    private Double price;
    private Integer quantity;
}
