package com.uet.hightex.enums.item;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {
    SMARTPHONE(1, "Smartphone", "SMARTPHONE", "Điện thoại thông minh"),
    LAPTOP(2, "Laptop", "LAPTOP", "Máy tính xách tay"),
    TABLET(3, "Tablet", "TABLET", "Máy tính bảng"),
    ACCESSORY(4, "Accessory", "ACCESSORY", "Phụ kiện");

    private final int value;
    private final String name;
    private final String code;
    private final String description;
}
