package com.example.WarriorsTest.controller;


import com.example.WarriorsTest.models.DTO.EquippedItemsDTO;
import com.example.WarriorsTest.models.DTO.UserItemDTO;
import com.example.WarriorsTest.models.entity.EquippedItemsEntity;
import com.example.WarriorsTest.models.entity.HeroEntity;
import com.example.WarriorsTest.models.entity.ItemEntity;
import com.example.WarriorsTest.services.InventoryService;
import com.example.WarriorsTest.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public InventoryController(InventoryService inventoryService, UserService userService, ModelMapper modelMapper) {
        this.inventoryService = inventoryService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String getInventory(Model model, Principal principal) {

        HeroEntity hero = userService.findByUsername(principal.getName()).getHero();

        List<UserItemDTO> inventory = inventoryService.getInventory(principal.getName());

        EquippedItemsDTO equippedItemsDTO = modelMapper.map(hero.getEquipped(), EquippedItemsDTO.class);

        model.addAttribute("equippedItems", equippedItemsDTO.getEquippedItemsIDs());
        model.addAttribute("inventory", inventory);

        return "Inventory";
    }


}
