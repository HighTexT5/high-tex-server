package com.uet.hightex.components;

import com.uet.hightex.objects.CustomAuthenticationToken;
import com.uet.hightex.services.support.UserLoginService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserLoginService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserLoginService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }

        CustomAuthenticationToken authToken = (CustomAuthenticationToken) authentication;
        String username = authToken.getName();
        String hashPassword = authToken.getHashPassword();
        String timestamp = authToken.getTimestamp();

//        if (Math.abs(Instant.now().getEpochSecond() - timestamp) > 180) { // 180 giây = 3 phút
//            throw new BadCredentialsException("Timestamp is invalid or expired");
//        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("User not found");
        }

        // Tạo hash password từ timestamp và so sánh với hash password nhận được
//        String serverHash = passwordEncoder.encode(userDetails.getPassword() + timestamp);
//        if (!serverHash.equals(hashPassword)) {
//            throw new BadCredentialsException("Invalid credentials");
//        }
        // Tạm thời dùng password thường
        if (!hashPassword.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        // Tạo CustomAuthenticationToken mới với trạng thái đã xác thực và authorities
        return new CustomAuthenticationToken(
                username,
                hashPassword,
                timestamp,
                userDetails.getAuthorities() // Danh sách authorities từ UserDetails
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.isAssignableFrom(authentication);
    }
}