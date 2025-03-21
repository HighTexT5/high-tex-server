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
public class RequestSendOpinionOfManagerDto {
    @Schema(description = "Request id")
    private Long requestId;

    @Schema(description = "Opinion")
    private String opinion;

    @Schema(description = "Detail")
    private String detail;
}
