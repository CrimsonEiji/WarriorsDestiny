package com.example.WarriorsTest.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/battle")
public class BattleController {

    @GetMapping
    public String getBattle(){
        return "Battle";
    }

}
