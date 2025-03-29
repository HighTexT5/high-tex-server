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
public class ResponseItemInCart {
    @Schema(description = "Item name")
    private String itemName;

    @Schema(description = "Quantity")
    private int quantity;

    @Schema(description = "Current price")
    private double currentPrice;

    @Schema(description = "Thumbnail URL")
    private String thumbnailUrl;
}
