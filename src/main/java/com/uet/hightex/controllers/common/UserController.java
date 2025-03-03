package com.uet.hightex.controllers.common;

import com.uet.hightex.dtos.base.BaseResponse;
import com.uet.hightex.dtos.base.ResponsePage;
import com.uet.hightex.dtos.common.RequestUserSignUpDto;
import com.uet.hightex.services.common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (userService.signup(requestUserSignUpDto.getUsername(), requestUserSignUpDto.getPassword())) {
            return new BaseResponse<>(200, "Success", "User created");
        }
        return new BaseResponse<>(400, "Fail", "User already exist");
    }
}
