package com.uet.hightex.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@AllArgsConstructor
@Getter
public enum AppConstant {
    REQUEST_SUCCESS(200, "common", "Request success"),

    REQUEST_ERROR_SIGN_UP(400, "authentication", "Request error sign up when username is already exist"),
    REQUEST_ERROR_OTP(401, "authentication", "Request error sign up when OTP is not correct"),
    REQUEST_ERROR_SIGN_IN(402, "authentication", "Request error sign in when username or password is not correct"),

    REQUEST_GET_USER_DATA_FAIL(403, "user", "Request get user data fail"),
    REQUEST_UPDATE_USER_DATA_FAIL(404, "user", "Request update user data fail"),

    REQUEST_ERROR(500, "common", "Request error");

    private final int value;
    private final String group;
    private final String name;
}
