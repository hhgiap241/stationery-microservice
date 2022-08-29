package com.kms.giaphoang.inventoryservice.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/29/2022, Monday
 * @project: spring-boot-stationery
 **/
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @SequenceGenerator(name = "inventory_seq", sequenceName = "inventory_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_seq")
    private Long id;
    private String skuCode; // A stock-keeping unit (SKU) is a scannable bar code, most often seen printed on product labels in a retail store.
    private Integer quantity;

}
