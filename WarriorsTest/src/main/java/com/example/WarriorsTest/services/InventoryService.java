package com.example.WarriorsTest.services;


import com.example.WarriorsTest.enums.HeroClass;
import com.example.WarriorsTest.enums.Material;
import com.example.WarriorsTest.exeptions.item.ItemIncompatibleException;
import com.example.WarriorsTest.exeptions.item.ItemIsEquippedException;
import com.example.WarriorsTest.models.DTO.EquippedItemsDTO;
import com.example.WarriorsTest.models.DTO.UserItemDTO;
import com.example.WarriorsTest.models.entity.EquippedItemsEntity;
import com.example.WarriorsTest.models.entity.HeroEntity;
import com.example.WarriorsTest.models.entity.ItemEntity;
import com.example.WarriorsTest.models.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class InventoryService {


    private final UserService userService;
    private final ModelMapper modelMapper;
    private final HeroService heroService;
    private final ItemService itemService;

    public InventoryService(UserService userService, ModelMapper modelMapper, HeroService heroService, ItemService itemService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.heroService = heroService;
        this.itemService = itemService;
    }

    public List<UserItemDTO> getInventory(String username) {
        UserEntity potentialUser = userService.findByUsername(username);

        return potentialUser.getHero().getInventory().stream()
                .map(item -> modelMapper.map(item, UserItemDTO.class)).toList();
    }

    public void equipItem(String username, long itemID) {
        HeroEntity hero = userService
                .findByUsername(username).getHero();
        ItemEntity item = itemService.findById(itemID);
        EquippedItemsEntity equipped = hero.getEquipped();
        switch (item.getType()) {
            case BOW -> {
                if (hero.getHeroClass() != HeroClass.HUNTER) {
                    throw new ItemIncompatibleException(hero.getHeroClass(), item.getType(), itemID);
                }
                equipped.setWeapon(item);
                hero.getStats().setAttack(hero.getStats().getAttack() + item.getStat());
            }
            case STAFF -> {
                if (hero.getHeroClass() != HeroClass.MAGE) {
                    throw new ItemIncompatibleException(hero.getHeroClass(), item.getType(), itemID);
                }
                equipped.setWeapon(item);
                hero.getStats().setAttack(hero.getStats().getAttack() + item.getStat());
            }
            case SWORD -> {
                if (hero.getHeroClass() != HeroClass.KNIGHT) {
                    throw new ItemIncompatibleException(hero.getHeroClass(), item.getType(), itemID);
                }
                equipped.setWeapon(item);
                hero.getStats().setAttack(hero.getStats().getAttack() + item.getStat());
            }
            case HELMET -> {
                if (hero.getHeroClass() != HeroClass.KNIGHT && item.getMaterial() == Material.PLATE) {
                    throw new ItemIncompatibleException(hero.getHeroClass(), item.getType(), itemID);
                }
                equipped.setHelmet(item);
                hero.getStats().setArmour(hero.getStats().getArmour() + item.getStat());
            }
            case BOOTS -> {
                if (hero.getHeroClass() != HeroClass.KNIGHT && item.getMaterial() == Material.PLATE) {
                    throw new ItemIncompatibleException(hero.getHeroClass(), item.getType(), itemID);
                }
                equipped.setBoots(item);
                hero.getStats().setArmour(hero.getStats().getArmour() + item.getStat());
            }
            case CHEST -> {
                if (hero.getHeroClass() != HeroClass.KNIGHT && item.getMaterial() == Material.PLATE) {
                    throw new ItemIncompatibleException(hero.getHeroClass(), item.getType(), itemID);
                }
                equipped.setChest(item);
                hero.getStats().setArmour(hero.getStats().getArmour() + item.getStat());
            }
            case HEALTH_POTION -> {
                int healthToSet = hero.getStats().getCurrentHealth() +item.getStat();
                if (healthToSet >hero.getStats().getHealth()){
                    healthToSet = hero.getStats().getHealth();
                }
                hero.getStats().setCurrentHealth(healthToSet);
                hero.getInventory().remove(item);
                itemService.deleteItem(item);
            }
            case MANA_POTION -> {
                int manaToSet = hero.getStats().getCurrentMana() +item.getStat();
                if (manaToSet>hero.getStats().getMana()){
                    manaToSet = hero.getStats().getMana();
                }
                hero.getStats().setCurrentMana(manaToSet);
                hero.getInventory().remove(item);
                itemService.deleteItem(item);
            }
        }
        heroService.saveAndFlush(hero);
    }

    public void unEquipItem(String username, long itemID) {
        HeroEntity hero = userService
                .findByUsername(username).getHero();

        ItemEntity item = itemService.findById(itemID);
        EquippedItemsEntity equipped = hero.getEquipped();
        switch (item.getType()) {
            case BOW, STAFF, SWORD -> {
                if (Objects.equals(equipped.getWeapon().getId(), item.getId())) {
                    equipped.setWeapon(null);
                    hero.getStats().setAttack(hero.getStats().getAttack() - item.getStat());
                }
            }
            case HELMET -> {
                if (Objects.equals(equipped.getHelmet().getId(), item.getId())) {
                    equipped.setHelmet(null);
                    hero.getStats().setArmour(hero.getStats().getArmour() - item.getStat());
                }
            }
            case BOOTS -> {
                if (Objects.equals(equipped.getBoots().getId(), item.getId())) {
                    equipped.setBoots(null);
                    hero.getStats().setArmour(hero.getStats().getArmour() - item.getStat());
                }
            }
            case CHEST -> {
                if (Objects.equals(equipped.getChest().getId(), item.getId())) {
                    equipped.setChest(null);
                    hero.getStats().setArmour(hero.getStats().getArmour() - item.getStat());
                }
            }
        }
        heroService.saveAndFlush(hero);
    }

    public void sellItem(String username, long itemID) {
        HeroEntity hero = userService.findByUsername(username).getHero();
        ItemEntity item = itemService.findById(itemID);
        hero.setGold(hero.getGold() + item.getPrice());
        deleteItem(username, itemID);
        heroService.saveAndFlush(hero);
    }

    public void deleteItem(String username, long itemID) {
        HeroEntity hero = userService
                .findByUsername(username).getHero();
        ItemEntity item = itemService.findById(itemID);

        EquippedItemsDTO equippedItemsDTO = modelMapper.map(hero.getEquipped(), EquippedItemsDTO.class);

        if (!hero.getInventory().contains(item)) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        if (equippedItemsDTO.getEquippedItemsIDs().contains(item.getId()))
            throw new ItemIsEquippedException(itemID);
        hero.getInventory().remove(item);

        itemService.deleteItem(item);
    }
}
