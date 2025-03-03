package com.uet.hightex.services.impl.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserLockStatus {
    LOCKED(1, "LOCKED"),
    UNLOCKED(2, "UNLOCKED"),
    DELETED(3, "DELETED");

    private final int value;
    private final String name;

    public static UserLockStatus fromValue(int value) {
        for (UserLockStatus userLockStatus : UserLockStatus.values()) {
            if (userLockStatus.getValue() == value) {
                return userLockStatus;
            }
        }
        return null;
    }
}
