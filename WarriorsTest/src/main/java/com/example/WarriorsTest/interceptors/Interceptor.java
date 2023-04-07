package com.example.WarriorsTest.interceptors;

import com.example.WarriorsTest.services.HeroService;
import com.example.WarriorsTest.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class Interceptor implements HandlerInterceptor {

    private final HeroService heroService;

    public Interceptor(HeroService heroService) {

        this.heroService = heroService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (!heroService.doesUserHasHeroCreated(request.getUserPrincipal().getName())) {
            response.sendRedirect("/hero/error");
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
