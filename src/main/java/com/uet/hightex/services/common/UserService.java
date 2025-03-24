package com.uet.hightex.services.common;

import com.uet.hightex.dtos.common.RequestUserSignInDto;
import com.uet.hightex.dtos.common.RequestUserSignUpDto;
import com.uet.hightex.dtos.common.ResponseUserSignInDto;

public interface UserService {
    int signUp(RequestUserSignUpDto requestUserSignUpDto);

    ResponseUserSignInDto signIn(RequestUserSignInDto requestUserSignInDto);
}
