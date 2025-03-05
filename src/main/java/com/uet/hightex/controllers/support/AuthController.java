package com.uet.hightex.controllers.support;

import com.uet.hightex.dtos.base.BaseResponse;
import com.uet.hightex.enums.AppConstant;
import com.uet.hightex.services.common.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private OtpService otpService;

    @PostMapping("/register")
    public BaseResponse<String> register(@RequestParam String email, @RequestParam String username) {
        String otp = otpService.generateOtp(email, username);
        if (otp.length() > 6) {
            return new BaseResponse<>(AppConstant.REQUEST_ERROR_SIGN_UP.getValue(), "Error", otp);
        }
        otpService.storeOtp(email, otp + username);

        try {
            otpService.sendOtp(email, otp);
        } catch (Exception e) {
            return new BaseResponse<>(500, "Error", "Failed to send OTP to email");
        }

        return new BaseResponse<>(200, "Success", "OTP sent to email");
    }
}
