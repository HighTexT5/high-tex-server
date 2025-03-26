package com.uet.hightex.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestActiveAnItemDto {
    @Schema(description = "Manager name")
    private String managerName;

    @Schema(description = "Item nam")
    private String itemName;

    @Schema(description = "CATEGORY")
    private String category;

    @Schema(description = "BRAND")
    private String brand;

    @Schema(description = "PRODUCT_SOURCE")
    private String productSource;

    @Schema(description = "QUANTITY")
    private int quantity;

    @Schema(description = "PRICE")
    private double price;

    @Schema(description = "PROOF")
    private String proof;

    @Schema(description = "Description")
    private String description;

    @Schema(description = "Detail")
    private Object detail;
}
