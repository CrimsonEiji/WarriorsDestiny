package com.example.WarriorsTest.controller;

import com.example.WarriorsTest.error_strings.ErrorStrings;
import com.example.WarriorsTest.exeptions.inventory.InventoryFullException;
import com.example.WarriorsTest.exeptions.shop.ItemExpiredException;
import com.example.WarriorsTest.exeptions.shop.ShopException;
import com.example.WarriorsTest.exeptions.shop.ShopIsEmptyException;
import com.example.WarriorsTest.models.DTO.ShopItemDTO;
import com.example.WarriorsTest.services.ShopService;
import com.example.WarriorsTest.services.UserService;
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
    private final UserService userService;

    public ShopController(List<ShopItemDTO> list, ShopService shopService, UserService userService) {
        this.list = list;
        this.shopService = shopService;
        this.userService = userService;
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

        model.addAttribute("shopItem", list.get(itemID));
        model.addAttribute("id", itemID);

        return "ShopItem";
    }

    @PostMapping("/buy/{id}/{price}/{item}")
    public String buyItem(@PathVariable int id, @PathVariable String item,@PathVariable int price, Principal principal ) {


        if (userService.findByUsername(principal.getName()).getHero().getInventory().size()>40)
            throw new InventoryFullException(ErrorStrings.INVENTORY_FULL);

        if (!item.equals(list.get(id).toString())) throw new ItemExpiredException();

        shopService.buyItem(id, principal.getName(),price,item);

        return "redirect:/shop";
    }

    @GetMapping
    public String getShop(Model model) {

        if (list.isEmpty()){
            model.addAttribute("isError",true);
            model.addAttribute("errorMessage", ErrorStrings.SHOP_EMPTY);
        }

        model.addAttribute("shopList", list);
        return "Shop";
    }
}
