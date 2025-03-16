package com.uet.hightex.enums.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE(1, "MALE", "Nam"),
    FEMALE(2, "FEMALE", "Nữ"),
    OTHER(3, "OTHER", "Khác");

    private final int value;
    private final String code;
    private final String description;

    public static Gender fromValue(int value) {
        for (Gender gender : Gender.values()) {
            if (gender.getValue() == value) {
                return gender;
            }
        }
        return null;
    }
}
