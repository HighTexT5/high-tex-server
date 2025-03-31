package com.uet.hightex.dtos.order;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequestDto {
    // Getter and setter
    private String addressCode;
    // Default constructor (required for JSON deserialization)
    public OrderRequestDto() {}

}
