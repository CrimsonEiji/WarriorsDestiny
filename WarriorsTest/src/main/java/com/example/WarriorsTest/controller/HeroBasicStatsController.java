package com.example.WarriorsTest.controller;

import com.example.WarriorsTest.models.DTO.HeroRESTStatsDTO;
import com.example.WarriorsTest.models.entity.UserEntity;
import com.example.WarriorsTest.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("/api/hero-stats")
public class HeroBasicStatsController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public HeroBasicStatsController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<HeroRESTStatsDTO> getStatsToDisplay(Principal principal) {
        UserEntity user = userService.findByUsername(principal.getName());
        if (user.getHero() == null) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        HeroRESTStatsDTO hero = modelMapper.map(user.getHero(), HeroRESTStatsDTO.class);
        return new ResponseEntity<>(hero, HttpStatus.OK);
    }
}
