package com.uet.hightex.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AppConstant {
    REQUEST_SUCCESS(200, "common", "Request success"),

    REQUEST_ERROR_SIGN_UP(400, "authentication", "Request error sign up when username is already exist"),
    REQUEST_ERROR_OTP(401, "authentication", "Request error sign up when OTP is not correct");


    private final int value;
    private final String group;
    private final String name;
}
