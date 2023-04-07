package com.example.WarriorsTest.services;

import com.example.WarriorsTest.enums.HeroClass;
import com.example.WarriorsTest.enums.Material;
import com.example.WarriorsTest.exeptions.item.ItemIncompatible;
import com.example.WarriorsTest.exeptions.item.ItemIsEquippedException;
import com.example.WarriorsTest.models.DTO.EquippedItemsDTO;
import com.example.WarriorsTest.models.entity.EquippedItemsEntity;
import com.example.WarriorsTest.models.entity.HeroEntity;
import com.example.WarriorsTest.models.entity.ItemEntity;
import com.example.WarriorsTest.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final HeroService heroService;

    public ItemService(ItemRepository itemRepository, UserService userService, ModelMapper modelMapper, HeroService heroService) {
        this.itemRepository = itemRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.heroService = heroService;
    }

    public ItemEntity findById(long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void saveAndFlush(ItemEntity item) {
        itemRepository.saveAndFlush(item);
    }

    public void save(ItemEntity item) {
        itemRepository.save(item);
    }

    public boolean isItemOnUser(String username, ItemEntity item) {
        return userService.findByUsername(username)
                .getHero().getInventory().contains(item);
    }

    public boolean isItemEquipped(long itemId, String username) {
        EquippedItemsEntity equippedItems = userService.findByUsername(username)
                .getHero().getEquipped();
        EquippedItemsDTO equippedItemsDTO = modelMapper.map(equippedItems, EquippedItemsDTO.class);

        return equippedItemsDTO.getEquippedItemsIDs().contains(itemId);
    }

    public void deleteItem(String username, long itemID) {
        HeroEntity hero = userService
                .findByUsername(username).getHero();
        ItemEntity item = this.findById(itemID);

        EquippedItemsDTO equippedItemsDTO = modelMapper.map(hero.getEquipped(), EquippedItemsDTO.class);

        if (!hero.getInventory().contains(item)) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        if (equippedItemsDTO.getEquippedItemsIDs().contains(item.getId()))
            throw new ItemIsEquippedException(itemID);
        hero.getInventory().remove(item);

        itemRepository.delete(item);
    }

    public void equipItem(String username, long itemID) {
        HeroEntity hero = userService
                .findByUsername(username).getHero();
        ItemEntity item = this.findById(itemID);
        EquippedItemsEntity equipped = hero.getEquipped();
        switch (item.getType()) {
            case BOW -> {
                if (hero.getHeroClass() != HeroClass.HUNTER) {
                    throw new ItemIncompatible(hero.getHeroClass(),item.getType(), itemID);
                }
                equipped.setWeapon(item);
            }
            case STAFF -> {
                if (hero.getHeroClass() != HeroClass.MAGE) {
                    throw new ItemIncompatible(hero.getHeroClass(),item.getType(), itemID);
                }
                equipped.setWeapon(item);
            }
            case SWORD -> {
                if (hero.getHeroClass() != HeroClass.KNIGHT) {
                    throw new ItemIncompatible(hero.getHeroClass(),item.getType(), itemID);
                }
                equipped.setWeapon(item);
            }
            case HELMET -> {
                if (hero.getHeroClass() != HeroClass.KNIGHT && item.getMaterial() == Material.PLATE) {
                    throw new ItemIncompatible(hero.getHeroClass(),item.getType(), itemID);
                }
                equipped.setHelmet(item);
            }
            case BOOTS -> {
                if (hero.getHeroClass() != HeroClass.KNIGHT && item.getMaterial() == Material.PLATE) {
                    throw new ItemIncompatible(hero.getHeroClass(),item.getType(), itemID);
                }
                equipped.setBoots(item);
            }
            case CHEST -> {
                if (hero.getHeroClass() != HeroClass.KNIGHT && item.getMaterial() == Material.PLATE) {
                    throw new ItemIncompatible(hero.getHeroClass(),item.getType(), itemID);
                }
                equipped.setChest(item);
            }
        }
        heroService.saveAndFlush(hero);
    }

    public void unEquipItem(String username, long itemID) {
        HeroEntity hero = userService
                .findByUsername(username).getHero();

        ItemEntity item = this.findById(itemID);
        EquippedItemsEntity equipped = hero.getEquipped();
        switch (item.getType()) {
            case BOW, STAFF, SWORD -> {
                if (Objects.equals(equipped.getWeapon().getId(), item.getId())) {
                    equipped.setWeapon(null);
                }
            }
            case HELMET -> {
                if (Objects.equals(equipped.getHelmet().getId(), item.getId())) {
                    equipped.setHelmet(null);
                }
            }
            case BOOTS -> {
                if (Objects.equals(equipped.getBoots().getId(), item.getId())) {
                    equipped.setBoots(null);
                }
            }
            case CHEST -> {
                if (Objects.equals(equipped.getChest().getId(), item.getId())) {
                    equipped.setChest(null);
                }
            }
        }
        heroService.saveAndFlush(hero);
    }
}
