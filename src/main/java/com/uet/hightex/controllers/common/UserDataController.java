package com.uet.hightex.controllers.common;

import com.uet.hightex.dtos.base.BaseResponse;
import com.uet.hightex.dtos.common.RequestUpdateUserDataDto;
import com.uet.hightex.dtos.common.ResponseUserDataDto;
import com.uet.hightex.enums.AppConstant;
import com.uet.hightex.services.common.UserDataService;
import com.uet.hightex.services.support.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/au/user")
public class UserDataController {
    private final UserDataService userDataService;
    private final BaseService baseService;

    @Autowired
    public UserDataController(UserDataService userDataService, BaseService baseService) {
        this.userDataService = userDataService;
        this.baseService = baseService;
    }

    @GetMapping("/data")
    public BaseResponse<ResponseUserDataDto> getUserData() {
        try {
            String userCode = baseService.getUserCode();
            ResponseUserDataDto responseUserDataDto = userDataService.getUserData(userCode);
            return new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success", responseUserDataDto);
        } catch (Exception e) {
            return new BaseResponse<>(AppConstant.REQUEST_GET_USER_DATA_FAIL.getValue(), "Error", null);
        }
    }

    @GetMapping("/get-by-code")
    public BaseResponse<ResponseUserDataDto> getUserDataByCode(@RequestParam String userCode) {
        try {
            ResponseUserDataDto responseUserDataDto = userDataService.getUserData(userCode);
            return new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success", responseUserDataDto);
        } catch (Exception e) {
            return new BaseResponse<>(AppConstant.REQUEST_GET_USER_DATA_FAIL.getValue(), "Error", null);
        }
    }

    @PutMapping("/update")
    public BaseResponse<String> updateUserData(@RequestBody RequestUpdateUserDataDto requestUpdateUserDataDto) {
        try {
            String userCode = baseService.getUserCode();
            userDataService.updateUserData(userCode, requestUpdateUserDataDto);
        } catch (Exception e) {
            return new BaseResponse<>(AppConstant.REQUEST_UPDATE_USER_DATA_FAIL.getValue(), "Error", "Update user data fail");
        }


        return new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success", "Update user data successfully");
    }
}
