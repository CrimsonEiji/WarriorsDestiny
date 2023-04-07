package com.example.WarriorsTest.controller;


import com.example.WarriorsTest.Adventure;
import com.example.WarriorsTest.AdventureResult;
import com.example.WarriorsTest.RandomItemGenerator;
import com.example.WarriorsTest.error_strings.ErrorStrings;
import com.example.WarriorsTest.exeptions.battle.HeroWasNotInBattleException;
import com.example.WarriorsTest.exeptions.inventory.InventoryFullException;
import com.example.WarriorsTest.models.DTO.HeroForBattleDTO;
import com.example.WarriorsTest.models.DTO.ItemForInventoryDTO;
import com.example.WarriorsTest.models.entity.ItemEntity;
import com.example.WarriorsTest.models.entity.UserEntity;
import com.example.WarriorsTest.services.ItemService;
import com.example.WarriorsTest.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/battle")
public class BattleController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ItemService itemService;

    public BattleController(UserService userService, ModelMapper modelMapper, ItemService itemService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.itemService = itemService;
    }

    @GetMapping
    public String getBattle() {
        return "Battle";
    }

    @PostMapping("/start")
    public String startBattle(Principal principal) {

        if (userService.findByUsername(principal.getName()).getHero().getInventory().size() > 40)
            throw new InventoryFullException(String.format(ErrorStrings.INVENTORY_FULL + " " +
                    "To start a battle you need at least 6 free slots!"));

        UserEntity user = userService.findByUsername(principal.getName()).setInBattle(true);
        userService.saveAndFlush(user);
        return "redirect:/battle/result";
    }

    @GetMapping("/result")
    public String getResults(Principal principal, Model model) {
        UserEntity user = userService.findByUsername(principal.getName());
        if (!user.getInBattle()) throw new HeroWasNotInBattleException();

        HeroForBattleDTO hero = modelMapper.map(user.getHero(), HeroForBattleDTO.class);
        Adventure adventure = new Adventure();
        AdventureResult result = adventure.StartAdventure(hero);

        if (result.isDead()) {
            model.addAttribute("isDead", true);
            model.addAttribute("hero", user.getHero());
        } else {
            model.addAttribute("isDead", false);
            RandomItemGenerator randomItemGenerator = new RandomItemGenerator();
            List<ItemEntity> rewardItems = new ArrayList<>();
            for (int i = 0; i < result.getEnemiesBeaten().size(); i++) {
                ItemEntity item = randomItemGenerator.generate(result.getHero().getLevel());
                itemService.save(item);
                rewardItems.add(item);
            }
            HeroForBattleDTO returnedHero = result.getHero();
            System.out.println(returnedHero.getStats());
            user.getHero()
                    .setStats(returnedHero.getStats())
                    .updateExperience(result.getEnemiesBeaten().size() * 20)
                    .setGold(user.getHero().getGold() + result.getGoldFind())
                    .getInventory().addAll(rewardItems);
            user.setInBattle(false);
            userService.save(user);

            List<ItemForInventoryDTO> items = rewardItems.stream()
                    .map(item -> modelMapper.map(item, ItemForInventoryDTO.class)).toList();
            model.addAttribute("listEnemy", result.getEnemiesBeaten());
            model.addAttribute("goldReward", result.getGoldFind());
            model.addAttribute("rewardItems", items);
        }


        return "BattleResult";
    }

}
