package com.uet.hightex.dtos.item;

public class UpdateCartItemQuantityRequestDto {
    private String itemCode;
    private Integer quantity;

    // Getters and Setters
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // Default constructor
    public UpdateCartItemQuantityRequestDto() {
    }

    // Parameterized constructor
    public UpdateCartItemQuantityRequestDto(String itemCode, Integer quantity) {
        this.itemCode = itemCode;
        this.quantity = quantity;
    }

}
