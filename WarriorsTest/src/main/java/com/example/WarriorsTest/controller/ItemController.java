package com.example.WarriorsTest.controller;


import com.example.WarriorsTest.enums.HeroClass;
import com.example.WarriorsTest.enums.Material;
import com.example.WarriorsTest.exeptions.item.ItemException;
import com.example.WarriorsTest.exeptions.item.ItemIncompatible;
import com.example.WarriorsTest.exeptions.item.ItemIsEquippedException;
import com.example.WarriorsTest.models.DTO.EquippedItemsDTO;
import com.example.WarriorsTest.models.entity.EquippedItemsEntity;
import com.example.WarriorsTest.models.entity.HeroEntity;
import com.example.WarriorsTest.models.entity.ItemEntity;
import com.example.WarriorsTest.services.EquippedItemsService;
import com.example.WarriorsTest.services.HeroService;
import com.example.WarriorsTest.services.ItemService;
import com.example.WarriorsTest.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;


    public ItemController(ItemService itemService, UserService userService, EquippedItemsService equippedItemsService, HeroService heroService, ModelMapper modelMapper) {
        this.itemService = itemService;
    }

    @ExceptionHandler({ItemIncompatible.class, ItemIsEquippedException.class})
    public String equipItemError(ItemException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        redirectAttributes.addFlashAttribute("isError", true);

        return "redirect:/items/details/" + e.getId();
    }

    @PostMapping("/equip/{itemID}")
    public String equipItem(@PathVariable Long itemID, Principal principal) {

        itemService.equipItem(principal.getName(), itemID);

        return "redirect:/inventory";
    }

    @PostMapping("/unEquip/{itemID}")
    public String unEquipItem(@PathVariable Long itemID, Principal principal) {

        itemService.unEquipItem(principal.getName(), itemID);

        return "redirect:/inventory";
    }

    @PostMapping("/delete/{itemID}")
    public String deleteItem(@PathVariable Long itemID, Principal principal) {

        itemService.deleteItem(principal.getName(), itemID);

        return "redirect:/inventory";
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
