package com.uet.hightex.services.impl.common;

import com.uet.hightex.dtos.common.RequestUserSignInDto;
import com.uet.hightex.dtos.common.RequestUserSignUpDto;
import com.uet.hightex.dtos.common.ResponseUserSignInDto;
import com.uet.hightex.entities.common.User;
import com.uet.hightex.enums.common.UserLockStatus;
import com.uet.hightex.enums.common.UserType;
import com.uet.hightex.objects.CustomAuthenticationToken;
import com.uet.hightex.repositories.common.UserRepository;
import com.uet.hightex.services.common.OtpService;
import com.uet.hightex.services.common.UserDataService;
import com.uet.hightex.services.common.UserService;
import com.uet.hightex.services.support.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OtpService otpService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDataService userDataService;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            OtpService otpService,
            JwtService jwtService,
            @Lazy AuthenticationManager authenticationManager,
            UserDataService userDataService) {
        this.userRepository = userRepository;
        this.otpService = otpService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDataService = userDataService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        user.setCode(createUserCode(username));
        user.setType(UserType.USER.getValue());
        user.setLockStatus(UserLockStatus.UNLOCKED.getValue());
        user.setEmail(email);

        userRepository.save(user);

        userDataService.createUserData(user.getCode(), username, email);

        return 0;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResponseUserSignInDto signIn(RequestUserSignInDto requestUserSignInDto) {
        String username = requestUserSignInDto.getUsername();
        String password = requestUserSignInDto.getPassword();
        String timestamp = requestUserSignInDto.getTimestamp();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new CustomAuthenticationToken(username, password, timestamp));
        } catch (Exception e) {
            log.error("Sign in failed", e);
            throw new UsernameNotFoundException("Sign in failed");
        }
        if (authentication.isAuthenticated()) {
            return new ResponseUserSignInDto(username, jwtService.generateToken(username), Objects.requireNonNull(UserType.fromValue(user.getType())).getCode());
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

    private String createUserCode(String username) {
        String input = username + Instant.now().toEpochMilli();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes());

            String base64Hash = Base64.getEncoder().encodeToString(hashBytes);
            String hash = base64Hash.substring(0, 19);
            return "U" + hash;

        } catch (NoSuchAlgorithmException e) {
            log.error("Create user code failed", e);
            throw new RuntimeException("Hash algorithm not found", e);
        }
    }
}
