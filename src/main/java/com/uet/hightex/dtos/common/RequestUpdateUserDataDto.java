package com.uet.hightex.dtos.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateUserDataDto {
    @Schema(description = "Full name")
    private String fullName;

    @Schema(description = "Gender")
    private Integer gender;

    @Schema(description = "Birthday")
    private String birthday;

    @Schema(description = "Phone number")
    private String phoneNumber;

    @Schema(description = "Email")
    private String email;

    @Schema(description = "Address")
    private String address;
}
