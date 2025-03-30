package com.uet.hightex.entities.common;

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
@Table(name = "USER_RECEIVE_ADDRESS")
public class UserReceiveAddress extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ADDRESS_CODE")
    private String addressCode;

    @Column(name = "USER_CODE")
    private String userCode;

    @Column(name = "PROVINCE")
    private String province;

    @Column(name = "DISTRICT")
    private String district;

    @Column(name = "COMMUNE")
    private String commune;

    @Column(name = "DETAIL")
    private String detail;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "IS_DEFAULT")
    private boolean isDefault;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;
}
