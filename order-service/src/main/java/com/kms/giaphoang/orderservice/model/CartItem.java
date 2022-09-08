package com.kms.giaphoang.orderservice.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 9/8/2022, Thursday
 * @project: spring-boot-stationery-chain
 **/
@Entity
@Table(name="cart_item")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @SequenceGenerator(name = "cart_item_seq", sequenceName = "cart_item_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_seq")
    private Long id;
    private String skuCode;
    private Double price;
    private Integer quantity;
}
