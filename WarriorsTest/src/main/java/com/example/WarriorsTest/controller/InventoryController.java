package com.example.WarriorsTest.controller;


import com.example.WarriorsTest.models.DTO.UserItemDTO;
import com.example.WarriorsTest.models.entity.EquippedItemsEntity;
import com.example.WarriorsTest.models.entity.HeroEntity;
import com.example.WarriorsTest.models.entity.ItemEntity;
import com.example.WarriorsTest.models.entity.UserEntity;
import com.example.WarriorsTest.services.InventoryService;
import com.example.WarriorsTest.services.UserService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final UserService userService;

    public InventoryController(InventoryService inventoryService, UserService userService) {
        this.inventoryService = inventoryService;
        this.userService = userService;
    }

    @GetMapping
    public String getInventory(Model model, Principal principal) {
        HeroEntity hero = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getHero();
        List<UserItemDTO> inventory = inventoryService.getInventory(principal.getName());
        List<Long> equippedItemsIds = new ArrayList<>();
        EquippedItemsEntity equippedItems = hero.getEquipped();
        ItemEntity weapon = equippedItems.getWeapon();
        ItemEntity helmet = equippedItems.getHead();
        ItemEntity chest = equippedItems.getChest();
        ItemEntity boots = equippedItems.getBoots();
        if (weapon != null) equippedItemsIds.add(weapon.getId());
        if (helmet != null) equippedItemsIds.add(helmet.getId());
        if (chest != null) equippedItemsIds.add(chest.getId());
        if (boots != null) equippedItemsIds.add(boots.getId());
        model.addAttribute("equippedItems", equippedItemsIds);
        model.addAttribute("inventory", inventory);

        return "Inventory";
    }


}
