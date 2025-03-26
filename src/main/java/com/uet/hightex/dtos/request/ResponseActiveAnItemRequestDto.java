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
public class ResponseActiveAnItemRequestDto {
    @Schema(description = "Request ID")
    private Long requestId;

    @Schema(description = "User full name")
    private String fullName;

    @Schema(description = "Shop name")
    private String shopName;

    @Schema(description = "Item name")
    private String itemName;

    @Schema(description = "Status")
    private String status;
}
