package com.example.WarriorsTest.controller;

import com.example.WarriorsTest.RandomItemGenerator;
import com.example.WarriorsTest.enums.HeroClass;
import com.example.WarriorsTest.exeptions.HeroAlreadyCreatedException;
import com.example.WarriorsTest.models.DTO.HeroCreationDTO;
import com.example.WarriorsTest.models.DTO.HeroDTO;
import com.example.WarriorsTest.models.entity.*;
import com.example.WarriorsTest.services.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/hero")
public class HeroController {

    private final UserService userService;
    private final HeroService heroService;
    private final ModelMapper modelMapper;
    private final StatsService statsService;
    private final EquippedItemsService equippedItemsService;
    private final ItemService itemService;

    public HeroController(UserService userService, HeroService heroService, ModelMapper modelMapper, StatsService statsService, EquippedItemsService equippedItemsService, ItemService itemService) {
        this.userService = userService;
        this.heroService = heroService;
        this.modelMapper = modelMapper;
        this.statsService = statsService;
        this.equippedItemsService = equippedItemsService;
        this.itemService = itemService;
    }

    @GetMapping("/error")
    public String getHeroDoesntExistPage() {
        return "HeroNotFoundErrorPage";
    }


    @GetMapping("/creation")
    public String getHeroCreation(Principal principal) {
        Optional<UserEntity> user = userService.findByUsername(principal.getName());
        if (user.isEmpty()) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        else if (user.get().getHero() != null) return "index";
        else return "HeroCreation";
    }

    @GetMapping("/details/{heroID}")
    public String getHeroDetails(@PathVariable Long heroID, Model model) {
        HeroEntity hero = heroService.findHeroById(heroID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        HeroDTO heroDTO = modelMapper.map(hero, HeroDTO.class);

        model.addAttribute("hero", heroDTO);

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
        Optional<UserEntity> user = userService.findByUsername(principal.getName());
        RandomItemGenerator generator = new RandomItemGenerator();
        if (user.isPresent()) {
            if (user.get().getHero() != null) throw new HeroAlreadyCreatedException("You have already created a hero!");
            else {
                StatsEntity stats = new StatsEntity();
                HeroClass baseStats = heroCreationDTO.getHeroClass();
                stats.setHealth(baseStats.health)
                        .setMana(baseStats.mana)
                        .setCurrentHealth(baseStats.health)
                        .setCurrentMana(baseStats.mana)
                        .setAttack(baseStats.attack)
                        .setArmour(baseStats.armour)
                        .setStrength(baseStats.strength)
                        .setIntellect(baseStats.intellect)
                        .setAgility(baseStats.agility)
                        .setDodgeChance(0.10);

                UserEntity presentUser = user.get();
                EquippedItemsEntity equippedItemsEntity = new EquippedItemsEntity();
                equippedItemsService.save(equippedItemsEntity);
                List<ItemEntity> inventory = new ArrayList<>();
                for (int g = 0;g<4;g++){
                    ItemEntity item = generator.generate(g);
                    itemService.save(item);
                    inventory.add(item);
                }
                HeroEntity hero = new HeroEntity();
                hero.setLevel(1)
                        .setName(heroCreationDTO.getName())
                        .setSpells(new ArrayList<>())
                        .setStats(stats)
                        .setInventory(inventory)
                        .setHeroClass(heroCreationDTO.getHeroClass())
                        .setEquipped(equippedItemsEntity);
                statsService.save(stats);

                presentUser.setHero(hero);
                heroService.save(hero);
                userService.saveAndFlush(presentUser);
            }
        }
        return "redirect:/home";
    }

    @ModelAttribute("heroCreationDTO")
    public HeroCreationDTO userLoginDTO() {
        return new HeroCreationDTO();
    }
}
