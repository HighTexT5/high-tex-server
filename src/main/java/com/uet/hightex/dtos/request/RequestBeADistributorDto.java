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
public class RequestBeADistributorDto {
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
}
