package com.example.WarriorsTest.controller;


import com.example.WarriorsTest.models.entity.ItemEntity;
import com.example.WarriorsTest.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/details/{itemID}")
    public String getItemDetails(@PathVariable Long itemID, Model model, Principal principal) {

        ItemEntity item = itemService.findById(itemID);

        boolean isItemOnUser = itemService.isItemOnUser(principal.getName(), item);

        boolean isItemEquipped = itemService.isItemEquipped(itemID, principal.getName());

        model.addAttribute("item", item);
        model.addAttribute("isItemOnUser", isItemOnUser);
        model.addAttribute("isItemEquipped", isItemEquipped);

        return "ItemDetails";
    }
}
