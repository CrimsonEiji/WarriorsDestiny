package com.example.WarriorsTest.controller;


import com.example.WarriorsTest.models.entity.HeroEntity;
import com.example.WarriorsTest.services.HeroService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private final HeroService heroService;

    public HomeController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping
    public String getIndex(Model model){

    Page<HeroEntity> heroes = heroService.getIndexPageFirstUsers();

    model.addAttribute("heroes",heroes);

        return "index";
    }
}
