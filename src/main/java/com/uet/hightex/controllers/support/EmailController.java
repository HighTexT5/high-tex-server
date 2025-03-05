package com.uet.hightex.controllers.support;

import com.uet.hightex.services.support.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

//    @Autowired
//    private EmailService emailService;
//
//    @GetMapping("/send-email")
//    public String sendEmail(@AuthenticationPrincipal OAuth2User principal,
//                            @RequestParam(required = false) String email) throws MessagingException {
//        if (email != null) {
//            String otp = "123456"; // Tạo mã OTP (ví dụ)
//            emailService.sendOtpEmail(email, otp);
//            return "Email sent!";
//        }
//        return "redirect:/"; // Chuyển hướng nếu không có email
//    }
}
