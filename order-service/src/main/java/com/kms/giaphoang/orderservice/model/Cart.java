package com.kms.giaphoang.orderservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 9/8/2022, Thursday
 * @project: spring-boot-stationery-chain
 **/
@Entity
@Table(name="cart")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @SequenceGenerator(name = "cart_seq", sequenceName = "cart_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
    private Long id;
    private String userId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cart_id", referencedColumnName = "id")
    private List<CartItem> cartItems = new ArrayList<>();
}
