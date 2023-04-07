package com.example.WarriorsTest.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorRegistrationConfig implements WebMvcConfigurer {


    private final Interceptor interceptor;

    @Autowired
    public InterceptorRegistrationConfig(Interceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(interceptor)
                .addPathPatterns("/hero/details/my-hero",
                        "/inventory/**",
                        "/ranking/**",
                        "/shop/**",
                        "/battle/**",
                        "/items/**",
                        "/spells/**"
                )
                .excludePathPatterns("/hero/error");
    }
}