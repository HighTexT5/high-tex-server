package com.uet.hightex.controllers.common;

import com.uet.hightex.dtos.base.BaseResponse;
import com.uet.hightex.dtos.order.RequestCreateAddressDto;
import com.uet.hightex.dtos.order.ResponseUserReceiveAddress;
import com.uet.hightex.enums.AppConstant;
import com.uet.hightex.services.common.UserReceiveAddressService;
import com.uet.hightex.services.support.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receive-address")
public class UserReceiveAddressController {
    private final UserReceiveAddressService userReceiveAddressService;
    private final BaseService baseService;

    @Autowired
    public UserReceiveAddressController(UserReceiveAddressService userReceiveAddressService, BaseService baseService) {
        this.userReceiveAddressService = userReceiveAddressService;
        this.baseService = baseService;
    }

    @GetMapping("/user-all-address")
    public BaseResponse<List<ResponseUserReceiveAddress>> getAllUserReceiveAddress() {
        String userCode = baseService.getUserCode();
        return new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success", userReceiveAddressService.getAllUserReceiveAddress(userCode));
    }

    @PostMapping("/create-address")
    public BaseResponse<String> createUserReceiveAddress(@RequestBody RequestCreateAddressDto requestCreateAddressDto) {
        String userCode = baseService.getUserCode();
        userReceiveAddressService.createUserReceiveAddress(userCode, requestCreateAddressDto);
        return new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success", "Create address successfully");
    }
}
