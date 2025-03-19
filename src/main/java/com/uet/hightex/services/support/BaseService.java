package com.uet.hightex.services.support;

import com.uet.hightex.entities.common.User;
import com.uet.hightex.repositories.common.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    private final UserRepository userRepository;

    @Autowired
    public BaseService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserCode() throws RuntimeException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getCode();
    }
}
