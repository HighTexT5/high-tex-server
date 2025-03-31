package com.uet.hightex.configs;

import com.uet.hightex.components.CustomAuthenticationProvider;
import com.uet.hightex.components.JwtAuthFilter;
import com.uet.hightex.services.support.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {
    private final JwtAuthFilter authFilter;

    private final CustomAuthenticationProvider customAuthenticationProvider;

    public SecurityConfig(JwtAuthFilter authFilter,
                          @Lazy CustomAuthenticationProvider customAuthenticationProvider) {
        this.authFilter = authFilter;
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserLoginService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(
                        (requests) -> requests
                                .requestMatchers("/api/auth/**", "api/user/**", "api/item/**").permitAll()
                                .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers("/api/au/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_DISTRIBUTOR")
                                .requestMatchers("/api/request/user/**").hasAuthority("ROLE_USER")
                                .requestMatchers("/api/request/distributor/**").hasAuthority("ROLE_DISTRIBUTOR")
                                .requestMatchers("/api/request/admin/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers("/api/request/all/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_DISTRIBUTOR")
                                .requestMatchers("/api/order/all/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_DISTRIBUTOR")
                                .requestMatchers("/api/order/distributor/**").hasAuthority("ROLE_DISTRIBUTOR")
                                .anyRequest().authenticated()
                )
                .sessionManagement(
                        (session) -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(customAuthenticationProvider);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://172.21.144.1:3000")); // Cho phép truy cập từ origin này
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Cho phép các phương thức này
        configuration.setAllowedHeaders(List.of("*")); // Cho phép tất cả các headers
        configuration.setAllowCredentials(true); // Cho phép gửi cookie

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Áp dụng cấu hình CORS cho tất cả các đường dẫn
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Password encoding
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
