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

    public static Category fromValue(int value) {
        for (Category category : Category.values()) {
            if (category.getValue() == value) {
                return category;
            }
        }
        throw new IllegalArgumentException("No category found with value: " + value);
    }

    public static Category fromCode(String code) {
        for (Category category : Category.values()) {
            if (category.getCode().equalsIgnoreCase(code)) {
                return category;
            }
        }
        throw new IllegalArgumentException("No category found with code: " + code);
    }
}
