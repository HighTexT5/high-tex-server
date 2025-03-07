package com.uet.hightex.services.impl.common;

import com.uet.hightex.dtos.common.RequestUserSignUpDto;
import com.uet.hightex.entities.common.User;
import com.uet.hightex.enums.common.UserLockStatus;
import com.uet.hightex.enums.common.UserType;
import com.uet.hightex.repositories.common.UserRepository;
import com.uet.hightex.services.common.OtpService;
import com.uet.hightex.services.common.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OtpService otpService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, OtpService otpService) {
        this.userRepository = userRepository;
        this.otpService = otpService;
    }

    @Override
    public int signUp(RequestUserSignUpDto requestUserSignUpDto) {
        String username = requestUserSignUpDto.getUsername();
        String password = requestUserSignUpDto.getPassword();
        String email = requestUserSignUpDto.getEmail();
        String otp = requestUserSignUpDto.getOtp();
        if (!verifyOtp(email, otp, username)) {
            log.error("OTP is not correct");
            return 2;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCode(createUserCode());
        user.setType(UserType.USER.getValue());
        user.setLockStatus(UserLockStatus.UNLOCKED.getValue());
        user.setEmail(email);

        userRepository.save(user);

        return 0;
    }

    private boolean verifyOtp(String email, String otp, String username) {
        String storedOtp = otpService.getOtp(email);
        if (storedOtp != null && storedOtp.equals(otp + username)) {
            otpService.removeOtp(email);
            return true;
        }
        return false;
    }

    private String createUserCode() {
        return "U" + System.currentTimeMillis();
    }
}
