package com.kms.giaphoang.orderservice.model;

import com.kms.giaphoang.orderservice.model.enums.OrderStatus;
import lombok.*;
import org.springframework.boot.actuate.audit.listener.AuditListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/26/2022, Friday
 * @project: spring-boot-stationery
 **/
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="t_order")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    private Long id;
    private String userId;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private Double totalPrice;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id", referencedColumnName = "id")
    private List<OrderLineItems> orderLineItemsList;
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
