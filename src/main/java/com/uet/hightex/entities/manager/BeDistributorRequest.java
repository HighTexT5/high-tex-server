package com.uet.hightex.entities.manager;

import com.uet.hightex.entities.Request;
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
@Table(name = "BE_DISTRIBUTOR_REQUEST")
public class BeDistributorRequest extends Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SHOP_NAME")
    private String shopName;

    @Column(name = "SHOP_WAREHOUSE_ADDRESS")
    private String shopWarehouseAddress;

    @Column(name = "SHOP_PHONE")
    private String shopPhone;

    @Column(name = "SHOP_EMAIL")
    private String shopEmail;

    @Column(name = "SHOP_TAX_CODE")
    private String shopTaxCode;
}
