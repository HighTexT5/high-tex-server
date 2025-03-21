package com.uet.hightex.enums.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestStatus {
    PENDING(1, "pending", "Pending"),
    APPROVED(2, "approved", "Approved"),
    REJECTED(3, "rejected", "Rejected"),
    DELETED(4, "deleted", "Deleted");

    private final int value;
    private final String code;
    private final String description;

    public static RequestStatus fromValue(int value) {
        for (RequestStatus requestStatus : RequestStatus.values()) {
            if (requestStatus.getValue() == value) {
                return requestStatus;
            }
        }
        return null;
    }

    public static RequestStatus fromCode(String code) {
        for (RequestStatus requestStatus : RequestStatus.values()) {
            if (requestStatus.getCode().equals(code)) {
                return requestStatus;
            }
        }
        return null;
    }
}
