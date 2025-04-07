package com.uet.hightex.dtos.order;

import com.uet.hightex.entities.common.UserReceiveAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserReceiveAddress {
    @Schema(description = "Mã địa chỉ")
    private String addressCode;

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

    public static ResponseUserReceiveAddress fromEntity(UserReceiveAddress userReceiveAddress) {
        ResponseUserReceiveAddress responseUserReceiveAddress = new ResponseUserReceiveAddress();
        responseUserReceiveAddress.setAddressCode(userReceiveAddress.getAddressCode());
        responseUserReceiveAddress.setProvince(userReceiveAddress.getProvince());
        responseUserReceiveAddress.setDistrict(userReceiveAddress.getDistrict());
        responseUserReceiveAddress.setCommune(userReceiveAddress.getCommune());
        responseUserReceiveAddress.setDetail(userReceiveAddress.getDetail());
        responseUserReceiveAddress.setPhoneNumber(userReceiveAddress.getPhone());
        return responseUserReceiveAddress;
    }
}
