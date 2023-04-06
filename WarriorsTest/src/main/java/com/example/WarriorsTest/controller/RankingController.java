package com.example.WarriorsTest.controller;

import com.example.WarriorsTest.models.entity.UserEntity;
import com.example.WarriorsTest.services.HeroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ranking")
public class RankingController {
    private final HeroService heroService;

    public RankingController(HeroService heroService) {
        this.heroService = heroService;
    }


    @GetMapping
    public String getRanking(Model model) {
        model.addAttribute("ranking", heroService.findFirst100ByLevel()
                .getContent());
        return "Ranking";
    }

}
