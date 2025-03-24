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
@Table(name = "SHOP")
public class Shop extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SHOP_CODE")
    private String shopCode;

    @Column(name = "SHOP_NAME")
    private String shopName;

    @Column(name = "OWNER_CODE")
    private String ownerCode;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SHOP_ADDRESS")
    private String shopAddress;

    @Column(name = "SHOP_STATUS")
    private Integer shopStatus;

    @Column(name = "AVATAR_URL")
    private String avatarUrl;

    @Column(name = "TAX_CODE")
    private String taxCode;

    @Column(name = "NUMBER_OF_ITEMS")
    private Integer numberOfItems;

    @Column(name = "NUMBER_OF_ORDERS")
    private Integer numberOfOrders;

    @Column(name = "TOTAL_REVENUE")
    private Double totalRevenue;

    @Column(name = "TOTAL_SALES")
    private Integer totalSales;

    @Column(name = "PAYMENT_INFO_ID")
    private Long paymentInfoId;
}
