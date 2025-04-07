package com.uet.hightex.dtos.order;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AcceptOrderRequestDto {
    // Getters and Setters
    private String orderCode;

    // Default constructor
    public AcceptOrderRequestDto() {}

    // Parameterized constructor
    public AcceptOrderRequestDto(String orderCode) {
        this.orderCode = orderCode;
    }
}
