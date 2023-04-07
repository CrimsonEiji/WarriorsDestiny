package com.example.WarriorsTest.controller;

import com.example.WarriorsTest.models.entity.HeroEntity;
import com.example.WarriorsTest.models.entity.SpellEntity;
import com.example.WarriorsTest.models.entity.UserEntity;
import com.example.WarriorsTest.services.HeroService;
import com.example.WarriorsTest.services.SpellsService;
import com.example.WarriorsTest.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/spells")
public class SpellsController {
    private final SpellsService spellsService;
    private final UserService userService;
    private final HeroService heroService;

    public SpellsController(SpellsService spellsService, UserService userService, HeroService heroService) {
        this.spellsService = spellsService;
        this.userService = userService;
        this.heroService = heroService;
    }

    @GetMapping
    public String getSpells(Principal principal, Model model) {
        UserEntity user = userService.findByUsername(principal.getName());
        List<SpellEntity> spells = spellsService.findSpellsByHeroClass(user.getHero().getHeroClass());
        model.addAttribute("spells", spells);
        SpellEntity spell = user.getHero().getSpell();
        long spellId = -1;
        if (spell != null) {
            spellId = user.getHero().getSpell().getId();
        }
        model.addAttribute("equippedSpell", spellId);
        return "Spells";
    }

    @PostMapping("/equip/{spellID}")
    public String equipSpell(Principal principal, Model model, @PathVariable long spellID) {
        HeroEntity hero = userService.findByUsername(principal.getName()).getHero();
        SpellEntity spell = spellsService.findSpellsByID(spellID);
        if (hero.getSpell()!= null && hero.getSpell().getId() == spellID) {
            hero.setSpell(null);
        } else {
            hero.setSpell(spell);
        }

        heroService.saveAndFlush(hero);

        return "redirect:/spells";
    }
}
