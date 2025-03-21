package com.uet.hightex.services.common;

import com.uet.hightex.dtos.common.RequestUpdateUserDataDto;
import com.uet.hightex.dtos.common.ResponseUserDataDto;

public interface UserDataService {
    void createUserData(String userCode, String fullName);

    ResponseUserDataDto getUserData(String userCode);

    void updateUserData(String userCode, RequestUpdateUserDataDto requestUpdateUserDataDto);
}
