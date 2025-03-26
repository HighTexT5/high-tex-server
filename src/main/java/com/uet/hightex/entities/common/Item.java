package com.uet.hightex.entities.common;

import com.uet.hightex.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ITEM")
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ITEM_CODE")
    private String itemCode;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "SHOP_CODE")
    private String shopCode;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "PRODUCT_SOURCE")
    private String productSource;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "ORIGIN_PRICE")
    private double originPrice;

    @Column(name = "CURRENT_PRICE")
    private double currentPrice;

    @Column(name = "RATING")
    private double rating;

    @Column(name = "FILE_URLS", columnDefinition = "JSON")
    private List<String> fileUrls;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;

    @Column(name = "REVENUE")
    private double revenue;

    @Column(name = "SALES_AMOUNT")
    private int salesAmount;

    @Column(name = "ITEM_INFO_ID")
    private Long itemInfoId;
}
