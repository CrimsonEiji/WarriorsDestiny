package com.example.WarriorsTest.controller;

import com.example.WarriorsTest.exeptions.shop.ItemExpiredException;
import com.example.WarriorsTest.exeptions.shop.ShopException;
import com.example.WarriorsTest.exeptions.shop.ShopIsEmptyException;
import com.example.WarriorsTest.models.DTO.ShopItemDTO;
import com.example.WarriorsTest.services.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final List<ShopItemDTO> list;
    private final ShopService shopService;

    public ShopController(List<ShopItemDTO> list, ShopService shopService) {
        this.list = list;
        this.shopService = shopService;
    }

    @ExceptionHandler({ItemExpiredException.class, ShopIsEmptyException.class})
    public String shopItemExceptionHandler(ShopException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("isError", true);
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

        return "redirect:/shop";
    }

    @GetMapping("/details/{itemID}/{item}")
    public String getShopItem(@PathVariable int itemID, Model model, @PathVariable String item) {

        if (list.isEmpty()) throw new ShopIsEmptyException();

        if (!item.equals(list.get(itemID).toString())) throw new ItemExpiredException();

        model.addAttribute("item", list.get(itemID));
        model.addAttribute("id", itemID);

        return "ShopItem";
    }

    @PostMapping("/buy/{id}/{item}")
    public String buyItem(@PathVariable int id, @PathVariable String item, Principal principal) {


        if (!item.equals(list.get(id).toString())) throw new ItemExpiredException();

        shopService.buyItem(id, principal.getName());

        return "redirect:/shop";
    }

    @GetMapping
    public String getShop(Model model) {
        model.addAttribute("shopList", list);
        return "Shop";
    }
}
