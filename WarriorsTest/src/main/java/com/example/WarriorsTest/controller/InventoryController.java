package com.example.WarriorsTest.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inventory")
public class InventoryController {


    @GetMapping
    public String getInventory(){
        return "Inventory";
    }


}
