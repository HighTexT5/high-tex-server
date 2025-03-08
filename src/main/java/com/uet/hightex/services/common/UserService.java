package com.uet.hightex.services.common;

import com.uet.hightex.dtos.common.RequestUserSignInDto;
import com.uet.hightex.dtos.common.RequestUserSignUpDto;

public interface UserService {
    int signUp(RequestUserSignUpDto requestUserSignUpDto);

    String signIn(RequestUserSignInDto requestUserSignInDto);
}
