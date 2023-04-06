package com.example.WarriorsTest.controller;


import com.example.WarriorsTest.enums.HeroClass;
import com.example.WarriorsTest.enums.Material;
import com.example.WarriorsTest.exeptions.ItemIncompatible;
import com.example.WarriorsTest.models.entity.EquippedItemsEntity;
import com.example.WarriorsTest.models.entity.HeroEntity;
import com.example.WarriorsTest.models.entity.ItemEntity;
import com.example.WarriorsTest.services.EquippedItemsService;
import com.example.WarriorsTest.services.HeroService;
import com.example.WarriorsTest.services.ItemService;
import com.example.WarriorsTest.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final UserService userService;
    private final EquippedItemsService equippedItemsService;
    private final HeroService heroService;

    public ItemController(ItemService itemService, UserService userService, EquippedItemsService equippedItemsService, HeroService heroService) {
        this.itemService = itemService;
        this.userService = userService;
        this.equippedItemsService = equippedItemsService;
        this.heroService = heroService;
    }

    @ExceptionHandler(ItemIncompatible.class)
    public String equipItemError(ItemIncompatible e, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        redirectAttributes.addFlashAttribute("isError", true);

        System.out.println(e.getMessage());
    return "redirect:/items/details/"+e.getId();
    }

    @PostMapping("/equip/{itemID}")
    public String equipItem(@PathVariable Long itemID, Principal principal) {

        HeroEntity hero = userService
                .findByUsername(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getHero();
        ItemEntity item = itemService.findById(itemID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        EquippedItemsEntity equipped = hero.getEquipped();
        switch (item.getType()) {
            case BOW -> {
                if (hero.getHeroClass() != HeroClass.HUNTER) {
                    throw new ItemIncompatible("Hero of class " +
                            hero.getHeroClass().name() + " cannot equip weapon of type "
                            + item.getType().name(),itemID);
                }
                equipped.setWeapon(item);
            }
            case STAFF -> {
                if (hero.getHeroClass() != HeroClass.MAGE) {
                    throw new ItemIncompatible("Hero of class " +
                            hero.getHeroClass().name() + " cannot equip weapon of type "
                            + item.getType().name(),itemID);
                }
                equipped.setWeapon(item);
            }
            case SWORD -> {
                if (hero.getHeroClass() != HeroClass.KNIGHT) {
                    throw new ItemIncompatible("Hero of class " +
                            hero.getHeroClass().name() + " cannot equip weapon of type "
                            + item.getType().name(),itemID);
                }
                equipped.setWeapon(item);
            }
            case HELMET -> {
                if (hero.getHeroClass() != HeroClass.KNIGHT && item.getMaterial() == Material.PLATE) {
                    throw new ItemIncompatible("Hero of class " +
                            hero.getHeroClass().name() + " cannot equip armor of type "
                            + item.getMaterial().name(),itemID);
                }
                equipped.setHead(item);
            }
            case BOOTS -> {
                if (hero.getHeroClass() != HeroClass.KNIGHT && item.getMaterial() == Material.PLATE) {
                    throw new ItemIncompatible("Hero of class " +
                            hero.getHeroClass().name() + " cannot equip armor of type "
                            + item.getMaterial().name(),itemID);
                }
                equipped.setBoots(item);
            }
            case CHEST -> {
                if (hero.getHeroClass() != HeroClass.KNIGHT && item.getMaterial() == Material.PLATE) {
                    throw new ItemIncompatible("Hero of class " +
                            hero.getHeroClass().name() + " cannot equip armor of type "
                            + item.getMaterial().name(),itemID);
                }
                equipped.setChest(item);
            }
        }
        heroService.saveAndFlush(hero);

        return "redirect:/inventory";
    }

    @GetMapping("/details/{itemID}")
    public String getItemDetails(@PathVariable Long itemID, Model model, Principal principal) {

        ItemEntity item = itemService.findById(itemID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        boolean isItemOnUser = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getHero().getInventory().contains(item);


        model.addAttribute("item", item);
        model.addAttribute("isItemOnUser", isItemOnUser);

        return "ItemDetails";
    }
}
