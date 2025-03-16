package com.uet.hightex.entities.common;

import com.uet.hightex.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_DATA")
public class UserData extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_CODE")
    private String userCode;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "GENDER")
    private Integer gender;

    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "ROLE")
    private Integer role;

    @Column(name = "AVATAR_URL")
    private String avatarUrl;

    @Column(name = "REGION")
    private String region;
}
