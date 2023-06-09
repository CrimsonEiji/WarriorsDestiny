package com.example.WarriorsTest.config;

import com.example.WarriorsTest.enums.UserRoles;
import com.example.WarriorsTest.services.AppUserDetailsService;
import com.example.WarriorsTest.services.UserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
public class SecurityConfiguration {

    private final UserService userService;

    public SecurityConfiguration(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, SecurityContextRepository securityContextRepository) throws Exception {
        http.authorizeHttpRequests().requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/error/**", "/plugins/**", "/flags/**", "/fonts/**", "/", "/errors", "/auth/login-error", "/api/hero-stats").permitAll()
                .requestMatchers("/auth/login", "/auth/register").anonymous()
                .requestMatchers("/hero/**", "/spells/**").authenticated()
                .requestMatchers("/admin/**").hasRole(UserRoles.ADMIN.name())
                .requestMatchers("/items/delete/**").hasRole(UserRoles.MODERATOR.name())
                .anyRequest()
                .authenticated()
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .key("uniqueAndSecret")
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/hero/details/my-hero")
                .failureForwardUrl("/auth/login-error")
                .and()
                .logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                .securityContext()
                .securityContextRepository(securityContextRepository);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new AppUserDetailsService(userService);
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }
}
