package com.example.WarriorsTest.interceptors;

import com.example.WarriorsTest.models.entity.HeroEntity;
import com.example.WarriorsTest.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class Interceptor implements HandlerInterceptor {

    private final UserService userService;

    public Interceptor(UserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HeroEntity hero = userService
                .findByUsername(request.getUserPrincipal().getName()).get().getHero();
        System.out.println("SDASDASDADSADA");
        if (hero == null) {
            response.sendRedirect("/hero/error");
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
