package com.uet.hightex.dtos.item;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddProductToCartRequestDto {
    // Getters and Setters
    private String itemCode;
    private Integer quantity;

    // Default constructor
    public AddProductToCartRequestDto() {
    }

    // Parameterized constructor
    public AddProductToCartRequestDto(String itemCode, Integer quantity) {
        this.itemCode = itemCode;
        this.quantity = quantity;
    }
}
