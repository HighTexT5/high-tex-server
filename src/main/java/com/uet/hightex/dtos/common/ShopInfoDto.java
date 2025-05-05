package com.uet.hightex.dtos.common;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopInfoDto {
    private String shopCode;

    private String shopName;

    private String ownerCode;

    private String phoneNumber;

    private String email;

    private String shopAddress;

    private Integer shopStatus;

    private String avatarUrl;

    private String taxCode;

    private Integer numberOfItems;

    private Integer numberOfOrders;

    private Double totalRevenue;

    private Integer totalSales;
}
