package com.uet.hightex.services.impl.common;

import com.uet.hightex.dtos.common.RequestUserSignInDto;
import com.uet.hightex.dtos.common.RequestUserSignUpDto;
import com.uet.hightex.entities.common.User;
import com.uet.hightex.enums.common.UserLockStatus;
import com.uet.hightex.enums.common.UserType;
import com.uet.hightex.objects.CustomAuthenticationToken;
import com.uet.hightex.repositories.common.UserRepository;
import com.uet.hightex.services.common.OtpService;
import com.uet.hightex.services.common.UserService;
import com.uet.hightex.services.support.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OtpService otpService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            OtpService otpService,
            JwtService jwtService,
            @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.otpService = otpService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
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

    @Override
    public String signIn(RequestUserSignInDto requestUserSignInDto) {
        String username = requestUserSignInDto.getUsername();
        String password = requestUserSignInDto.getPassword();
        long timestamp = requestUserSignInDto.getTimestamp();

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new CustomAuthenticationToken(username, password, timestamp));
        } catch (Exception e) {
            log.error("Sign in failed", e);
            throw new UsernameNotFoundException("Sign in failed");
        }
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(username);
        } else {
            log.error("Sign in failed");
            throw new UsernameNotFoundException("Sign in failed");
        }
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
