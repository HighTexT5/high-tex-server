package com.uet.hightex.dtos.request.items;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SmartphoneRequest {

    @Schema(description = "SCREEN_SIZE")
    private double screenSize; // inch

    @Schema(description = "SCREEN_RESOLUTION")
    private String screenResolution;

    @Schema(description = "OS")
    private String os;

    @Schema(description = "SCREEN_TECHNOLOGY")
    private String screenTechnology;

    @Schema(description = "BACK_CAMERA")
    private String backCamera;

    @Schema(description = "FRONT_CAMERA")
    private String frontCamera;

    @Schema(description = "NFC")
    private boolean nfc;

    @Schema(description = "SIM")
    private String sim;

    @Schema(description = "SCREEN_FEATURE")
    private String screenFeature;

    @Schema(description = "COMPATIBLE")
    private String compatible;

    @Schema(description = "CHIPSET")
    private String chipset;

    @Schema(description = "CPU")
    private String cpu;

    @Schema(description = "MEMORY")
    private String memory;

    @Schema(description = "RAM")
    private String ram;

    @Schema(description = "BATTERY")
    private String battery;
}
