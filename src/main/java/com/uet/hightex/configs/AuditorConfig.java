package com.uet.hightex.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class AuditorConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
//        return () -> Optional.of("System");
        return () -> {
            var authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.isAuthenticated()) {
                String username = authentication.getName();

                if (!"anonymousUser".equals(username)) {
                    return Optional.of(username);
                }
            }

            return Optional.of("System");
        };
    }
}
