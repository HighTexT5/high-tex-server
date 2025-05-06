package com.uet.hightex.dtos.item;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateCartItemQuantityRequestDto {
    // Getters and Setters
    private String itemCode;
    private Integer quantity;

    // Default constructor
    public UpdateCartItemQuantityRequestDto() {
    }

    // Parameterized constructor
    public UpdateCartItemQuantityRequestDto(String itemCode, Integer quantity) {
        this.itemCode = itemCode;
        this.quantity = quantity;
    }

}
