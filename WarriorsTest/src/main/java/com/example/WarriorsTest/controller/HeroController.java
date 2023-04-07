package com.example.WarriorsTest.controller;

import com.example.WarriorsTest.models.DTO.EquippedItemsDTO;
import com.example.WarriorsTest.models.DTO.HeroCreationDTO;
import com.example.WarriorsTest.models.DTO.HeroDTO;
import com.example.WarriorsTest.models.entity.HeroEntity;
import com.example.WarriorsTest.models.entity.UserEntity;
import com.example.WarriorsTest.services.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/hero")
public class HeroController {

    private final UserService userService;
    private final HeroService heroService;
    private final ModelMapper modelMapper;

    public HeroController(UserService userService, HeroService heroService, ModelMapper modelMapper, StatsService statsService, EquippedItemsService equippedItemsService, ItemService itemService) {
        this.userService = userService;
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/error")
    public String getHeroDoesntExistPage() {
        return "HeroNotFoundErrorPage";
    }


    @GetMapping("/creation")
    public String getHeroCreation(Principal principal) {
        UserEntity user = userService.findByUsername(principal.getName());
        if (user.getHero() != null) return "index";
        else return "HeroCreation";
    }

    @GetMapping("/details/{heroID}")
    public String getHeroDetails(@PathVariable Long heroID, Model model) {
        HeroEntity hero = heroService.findHeroById(heroID);

        HeroDTO heroDTO = modelMapper.map(hero, HeroDTO.class);

        EquippedItemsDTO equippedItemsDTO = modelMapper.map(hero.getEquipped(), EquippedItemsDTO.class);

        model.addAttribute("hero", heroDTO);
        model.addAttribute("equippedItems", equippedItemsDTO.getEquippedItems());

        return "HeroDetails";
    }

    @PostMapping("/creation")
    public String postHeroCreation(@Valid @ModelAttribute HeroCreationDTO heroCreationDTO,
                                   BindingResult bindingResult,
                                   RedirectAttributes ra, Principal principal) {

        if (bindingResult.hasErrors()) {
            ra.addFlashAttribute("heroCreationDTO", heroCreationDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.heroCreationDTO", bindingResult);
            return "redirect:/hero/creation";
        }

        heroService.createHero(principal.getName(), heroCreationDTO);

        return "redirect:/home";
    }

    @ModelAttribute("heroCreationDTO")
    public HeroCreationDTO userLoginDTO() {
        return new HeroCreationDTO();
    }
}
