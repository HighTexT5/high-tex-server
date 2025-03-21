package com.uet.hightex.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBeADistributorRequestDto {
    @Schema(description = "Request ID")
    private Long requestId;

    @Schema(description = "User full name")
    private String fullName;

    @Schema(description = "User code")
    private String userCode;

    @Schema(description = "Shop name")
    private String shopName;

    @Schema(description = "Status")
    private String status;
}
