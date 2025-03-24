package com.uet.hightex.enums.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShopStatus {
    ACTIVE(1, "ACTIVE"),
    INACTIVE(2, "INACTIVE"),
    DELETED(3, "DELETED");

    private final int value;
    private final String name;

    public static ShopStatus fromValue(int value) {
        for (ShopStatus shopStatus : ShopStatus.values()) {
            if (shopStatus.getValue() == value) {
                return shopStatus;
            }
        }
        return null;
    }
}
