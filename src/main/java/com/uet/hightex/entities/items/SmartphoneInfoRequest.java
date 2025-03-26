package com.uet.hightex.entities.items;

import com.uet.hightex.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SMARTPHONE_INFO_REQUEST")
public class SmartphoneInfoRequest extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SCREEN_SIZE")
    private double screenSize; // inch

    @Column(name = "SCREEN_RESOLUTION")
    private String screenResolution;

    @Column(name = "OS")
    private String os;

    @Column(name = "SCREEN_TECHNOLOGY")
    private String screenTechnology;

    @Column(name = "BACK_CAMERA")
    private String backCamera;

    @Column(name = "FRONT_CAMERA")
    private String frontCamera;

    @Column(name = "NFC")
    private boolean nfc;

    @Column(name = "SIM")
    private String sim;

    @Column(name = "SCREEN_FEATURE")
    private String screenFeature;

    @Column(name = "COMPATIBLE")
    private String compatible;

    @Column(name = "CHIPSET")
    private String chipset;

    @Column(name = "CPU")
    private String cpu;

    @Column(name = "MEMORY")
    private String memory;

    @Column(name = "RAM")
    private String ram;

    @Column(name = "BATTERY")
    private String battery;
}
