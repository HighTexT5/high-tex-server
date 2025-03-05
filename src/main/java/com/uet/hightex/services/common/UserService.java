package com.uet.hightex.services.common;

import com.uet.hightex.dtos.common.RequestUserSignUpDto;
import org.springframework.stereotype.Service;

public interface UserService {
    int signup(RequestUserSignUpDto requestUserSignUpDto);
}
