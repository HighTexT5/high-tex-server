package com.uet.hightex.entities.manager;

import com.uet.hightex.entities.Request;
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
@Table(name = "ACTIVE_ITEM_REQUEST")
public class ActiveItemRequest extends Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SHOP_CODE")
    private String shopCode;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "PRODUCT_SOURCE")
    private String productSource;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "PROOF")
    private String proof;

    @Column(name = "ITEM_DETAIL_ID")
    private Long itemDetailId;
}
