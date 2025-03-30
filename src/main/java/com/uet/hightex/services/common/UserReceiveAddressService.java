package com.uet.hightex.services.common;

import com.uet.hightex.dtos.order.RequestCreateAddressDto;
import com.uet.hightex.dtos.order.ResponseUserReceiveAddress;

import java.util.List;

public interface UserReceiveAddressService {
    List<ResponseUserReceiveAddress> getAllUserReceiveAddress(String userCode);

    void createUserReceiveAddress(String userCode, RequestCreateAddressDto requestCreateAddressDto);
}
