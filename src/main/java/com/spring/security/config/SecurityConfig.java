package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailService() {
        List<UserDetails> users = List.of(
                User.builder()
                        .username("user01")
                        .password(passwordEncoder().encode("pwd01"))
                        .authorities(new SimpleGrantedAuthority("ROLE_USER"))
                        .build()
                , User.builder()
                        .username("user02")
                        .password(passwordEncoder().encode("pwd02"))
                        .authorities(new SimpleGrantedAuthority("ROLE_USER"))
                        .build());

        return new InMemoryUserDetailsManager(users);
    }

}
