package com.example.WarriorsTest.services;

import com.example.WarriorsTest.RandomItemGenerator;
import com.example.WarriorsTest.enums.HeroClass;
import com.example.WarriorsTest.exeptions.hero.HeroAlreadyCreatedException;
import com.example.WarriorsTest.models.DTO.HeroCreationDTO;
import com.example.WarriorsTest.models.entity.*;
import com.example.WarriorsTest.repository.HeroRepository;
import com.example.WarriorsTest.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HeroService {

    private final HeroRepository heroRepository;
    private final EquippedItemsService equippedItemsService;
    private final UserService userService;
    private final ItemRepository itemRepository;
    private final StatsService statsService;


    public HeroService(HeroRepository heroRepository, EquippedItemsService equippedItemsService, UserService userService, ItemRepository itemRepository, StatsService statsService) {
        this.heroRepository = heroRepository;
        this.equippedItemsService = equippedItemsService;
        this.userService = userService;
        this.itemRepository = itemRepository;

        this.statsService = statsService;
    }

    public void saveAndFlush(HeroEntity hero) {
        heroRepository.saveAndFlush(hero);
    }

    public void save(HeroEntity hero) {
        heroRepository.save(hero);
    }

    public void saveAllAndFlush(List<HeroEntity> heroes) {
        heroRepository.saveAllAndFlush(heroes);
    }

    public Optional<HeroEntity> getHeroByName(String name) {
        return heroRepository.findHeroEntityByName(name);
    }

    public Page<HeroEntity> findFirst100ByLevel() {
        return heroRepository.findAll(
                PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "level")));
    }

    public HeroEntity findHeroById(Long id){
        return heroRepository.findHeroEntityById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Page<HeroEntity> getPageableUsersStep100(int page){
        return heroRepository.findAll(
                PageRequest.of(page, 100, Sort.by(Sort.Direction.DESC, "level")));
    }

    public boolean doesUserHasHeroCreated(String username){
      return userService.findByUsername(username).getHero() != null;
    }

    public void createHero(String username,HeroCreationDTO heroCreationDTO){

        UserEntity user = userService.findByUsername(username);
        RandomItemGenerator generator = new RandomItemGenerator();

        if (user.getHero() != null) throw new HeroAlreadyCreatedException();
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

        EquippedItemsEntity equippedItemsEntity = new EquippedItemsEntity();
        equippedItemsService.save(equippedItemsEntity);
        List<ItemEntity> inventory = new ArrayList<>();
        for (int g = 0; g < 4; g++) {
            ItemEntity item = generator.generate(g);
            itemRepository.save(item);
            inventory.add(item);
        }
        HeroEntity hero = new HeroEntity();
        hero.setLevel(1)
                .setName(heroCreationDTO.getName())
                .setSpells(new ArrayList<>())
                .setStats(stats)
                .setInventory(inventory)
                .setHeroClass(heroCreationDTO.getHeroClass())
                .setEquipped(equippedItemsEntity)
                .setGold(0);
        statsService.save(stats);

        user.setHero(hero);
        heroRepository.save(hero);
        userService.saveAndFlush(user);
    }
}
