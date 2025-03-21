package com.uet.hightex.dtos.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {
    @Schema(description = "Created by")
    private String createdBy;

    @Schema(description = "Created date")
    private String createdDate;

    @Schema(description = "Modified by")
    private String modifiedBy;

    @Schema(description = "Modified date")
    private String modifiedDate;
}
