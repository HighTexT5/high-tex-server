package com.uet.hightex.entities.common;

import com.uet.hightex.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ORDER_CODE")
    private String orderCode;

    @Column(name = "USER_CODE")
    private String userCode;

    @Column(name = "ITEM_CODE")
    private String itemCode;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;

    @Column(name = "IS_PAID")
    private boolean isPaid;

    @Column(name = "IS_DELIVERED")
    private boolean isDelivered;

    @Column(name = "IS_CANCELED")
    private boolean isCanceled;

    @Column(name = "STATUS")
    private String status;
}
