package com.uet.hightex.dtos.item;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseItemDetail {
    @Schema(description = "ID of the item")
    private Long id;

    @Schema(description = "Item code")
    private String itemCode;

    @Schema(description = "Item name")
    private String itemName;

    @Schema(description = "Shop code")
    private String shopCode;

    @Schema(description = "Shop name")
    private String shopName;

    @Schema(description = "Category")
    private String category;

    @Schema(description = "Brand")
    private String brand;

    @Schema(description = "Product source")
    private String productSource;

    @Schema(description = "Quantity")
    private int quantity;

    @Schema(description = "Origin price")
    private double originPrice;

    @Schema(description = "Current price")
    private double currentPrice;

    @Schema(description = "Rating")
    private double rating;

    @Schema(description = "File URLs")
    private String[] fileUrls;

    @Schema(description = "Thumbnail URL")
    private String thumbnailUrl;

    @Schema(description = "Detail")
    private Object detail;
}
