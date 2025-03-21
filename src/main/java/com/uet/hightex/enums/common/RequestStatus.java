package com.uet.hightex.enums.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestStatus {
    PENDING(1, "pending", "Pending"),
    ACCEPTED(2, "accepted", "Accepted"),
    REJECTED(3, "rejected", "Rejected");

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
}
