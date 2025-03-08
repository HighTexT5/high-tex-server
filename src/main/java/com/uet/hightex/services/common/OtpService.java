package com.uet.hightex.services.common;

import com.uet.hightex.repositories.common.UserRepository;
import com.uet.hightex.services.support.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class OtpService {
    private final EmailService emailService;
    private final UserRepository userRepository;

    private final Map<String, String> otpStorage = new HashMap<>();

    @Autowired
    public OtpService(EmailService emailService, UserRepository userRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    public String generateOtp(String email, String username) {
        if (isUsernameExist(username)) {
            return "Username already exists";
        } else if (validateEmail(email) != null) {
            return "Email already exists";
        }

        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public void storeOtp(String email, String otp) {
        otpStorage.put(email, otp);
    }

    public String getOtp(String email) {
        return otpStorage.get(email);
    }

    public void removeOtp(String email) {
        otpStorage.remove(email);
    }

    public void sendOtp(String email, String otp) throws MessagingException {
        // Send OTP to email
        String subject = "HighTEx OTP Verification";
        String template = "otp-email-template";
        Context context = new Context();
        context.setVariable("otp", otp);
        try {
            emailService.sendEmail(email, subject, template, context);
        } catch (Exception e) {
            log.error("Failed to send OTP to email", e);
            throw new MessagingException("Failed to send OTP to email");
        }
    }

    private String validateEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            return "Email already exists";
        }
        return null;
    }

    private boolean isUsernameExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
