package com.uet.hightex.enums.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum UserType {
    USER(1, "User", "ROLE_USER", "Người dùng bình thường"),
    ADMIN(2, "Admin", "ROLE_ADMIN", "Quản trị viên"),
    DISTRIBUTOR(3, "Distributor", "ROLE_DISTRIBUTOR", "Nhà phân phối");

    private final int value;
    private final String name;
    private final String code;
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
