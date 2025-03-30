package com.uet.hightex.dtos.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateAddressDto {
    @Schema(description = "Province")
    private String province;

    @Schema(description = "District")
    private String district;

    @Schema(description = "Commune")
    private String commune;

    @Schema(description = "Detail")
    private String detail;

    @Schema(description = "Phone number")
    private String phoneNumber;
}
