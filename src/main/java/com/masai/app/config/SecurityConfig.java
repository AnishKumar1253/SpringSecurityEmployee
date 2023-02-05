package com.masai.app.config;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.masai.app.security.EmployeeDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {
private final UserDetailsService employeeDetailsService;
public SecurityConfig(EmployeeDetailsService employeeDetailsService) {
    this.employeeDetailsService = employeeDetailsService;
}

protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(employeeDetailsService);
}

protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .requestMatchers("/api/user").permitAll()
            .requestMatchers("/api/secure/user").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/api/admin").hasRole("ADMIN")
            .and().formLogin();
}

@Bean
public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
}
}