package com.uet.hightex.dtos.request;

import com.uet.hightex.dtos.base.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBeADistributorRequestDetailDto extends BaseDto {
    @Schema(description = "User full name")
    private String fullName;

    @Schema(description = "User code")
    private String userCode;

    @Schema(description = "Shop name")
    private String shopName;

    @Schema(description = "Shop address")
    private String shopWarehouseAddress;

    @Schema(description = "Shop phone")
    private String shopPhone;

    @Schema(description = "Shop email")
    private String shopEmail;

    @Schema(description = "Shop tax code")
    private String shopTaxCode;

    @Schema(description = "Description")
    private String description;

    @Schema(description = "Status")
    private String status;
}
