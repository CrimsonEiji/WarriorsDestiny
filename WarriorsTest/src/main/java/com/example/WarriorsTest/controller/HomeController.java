package com.example.WarriorsTest.controller;

import com.example.WarriorsTest.models.entity.HeroEntity;
import com.example.WarriorsTest.models.entity.UserEntity;
import com.example.WarriorsTest.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home() {

        return "index";
    }



}
