package com.uet.hightex.objects;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String hashPassword;
    private final long timestamp;

    public CustomAuthenticationToken(String username, String hashPassword, long timestamp) {
        super(username, null);
        this.hashPassword = hashPassword;
        this.timestamp = timestamp;
    }

    // Constructor cho trường hợp đã xác thực
    public CustomAuthenticationToken(String username, String hashPassword, long timestamp, Collection<? extends GrantedAuthority> authorities) {
        super(username, null, authorities); // Truyền authorities vào constructor
        this.hashPassword = hashPassword;
        this.timestamp = timestamp;
    }

}