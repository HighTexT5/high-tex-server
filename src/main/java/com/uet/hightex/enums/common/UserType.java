package com.uet.hightex.enums.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum UserType {
    USER(1, "User", "Người dùng bình thường"),
    ADMIN(2, "Admin", "Quản trị viên"),
    DISTRIBUTOR(3, "Distributor", "Nhà phân phối");

    private final int value;
    private final String name;
    private final String description;

    public static UserType fromValue(int value) {
        for (UserType userType : UserType.values()) {
            if (userType.getValue() == value) {
                return userType;
            }
        }
        return null;
    }
}
