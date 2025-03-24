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
public class ResponseUserSignInDto {
    @Schema(description = "Username")
    private String username;

    @Schema(description = "Token")
    private String token;

    @Schema(description = "Role")
    private String role;
}
