package com.uet.hightex.controllers.common;

import com.uet.hightex.dtos.base.BaseResponse;
import com.uet.hightex.dtos.common.RequestUserSignInDto;
import com.uet.hightex.dtos.common.RequestUserSignUpDto;
import com.uet.hightex.dtos.common.ResponseUserSignInDto;
import com.uet.hightex.enums.AppConstant;
import com.uet.hightex.services.common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public BaseResponse<String> test() {
        return new BaseResponse<>(200, "Success", "Test");
    }

    @PostMapping("/signup")
    public BaseResponse<String> signup(@RequestBody RequestUserSignUpDto requestUserSignUpDto) {
        return switch (userService.signUp(requestUserSignUpDto)) {
            case 0 -> new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success", "Sign up successfully");
            case 1 ->
                    new BaseResponse<>(AppConstant.REQUEST_ERROR_SIGN_UP.getValue(), "Error", "Username is already exist");
            case 2 -> new BaseResponse<>(AppConstant.REQUEST_ERROR_OTP.getValue(), "Error", "OTP is not correct");
            default -> new BaseResponse<>(500, "Error", "Internal server error");
        };
    }

    @PostMapping("/sign-in")
    public BaseResponse<ResponseUserSignInDto> signIn(@RequestBody RequestUserSignInDto requestUserSignInDto) {
        try {
            return new BaseResponse<>(AppConstant.REQUEST_SUCCESS.getValue(), "Success", userService.signIn(requestUserSignInDto));
        } catch (UsernameNotFoundException e) {
            return new BaseResponse<>(AppConstant.REQUEST_ERROR_SIGN_IN.getValue(), e.getMessage(), null);
        } catch (Exception e) {
            return new BaseResponse<>(500, "Internal server error", null);
        }
    }
}
