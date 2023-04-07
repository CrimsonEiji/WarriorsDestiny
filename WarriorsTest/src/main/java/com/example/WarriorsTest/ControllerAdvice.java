package com.example.WarriorsTest;


import com.example.WarriorsTest.exeptions.hero.HeroAlreadyCreatedException;
import com.example.WarriorsTest.exeptions.inventory.InventoryFullException;
import com.example.WarriorsTest.exeptions.item.ItemException;
import com.example.WarriorsTest.exeptions.item.ItemIncompatibleException;
import com.example.WarriorsTest.exeptions.item.ItemIsEquippedException;
import com.example.WarriorsTest.exeptions.shop.NotEnoughGoldException;
import com.example.WarriorsTest.exeptions.user_role_change.AdminPanelException;
import com.example.WarriorsTest.exeptions.user_role_change.UserDoesntExistException;
import com.example.WarriorsTest.exeptions.user_role_change.UserIsDefaultAdminException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler({ItemIncompatibleException.class, ItemIsEquippedException.class})
    public String equipItemError(ItemException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        redirectAttributes.addFlashAttribute("isError", true);

        return "redirect:/items/details/" + e.getId();
    }

    @ExceptionHandler(HeroAlreadyCreatedException.class)
    public String equipItemError(HeroAlreadyCreatedException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        redirectAttributes.addFlashAttribute("isError", true);

        return "error/HeroAlreadyCreated";
    }

    @ExceptionHandler(InventoryFullException.class)
    public String inventoryFullExceptionHandle(InventoryFullException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        redirectAttributes.addFlashAttribute("isError", true);

        return "redirect:/inventory";
    }

    @ExceptionHandler(NotEnoughGoldException.class)
    public String notEnoughGoldError(NotEnoughGoldException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        redirectAttributes.addFlashAttribute("isError", true);

        return "redirect:/shop/details/" + e.getId() + "/" + e.getItemObjectName();
    }


    @ExceptionHandler({UserIsDefaultAdminException.class, UserDoesntExistException.class})
    public String userDoesntExistExceptionHandler(AdminPanelException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("isError", true);
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        redirectAttributes.addFlashAttribute("isAdd", e.isAdd());

        return "redirect:/admin/changeUserRoles";
    }
}
