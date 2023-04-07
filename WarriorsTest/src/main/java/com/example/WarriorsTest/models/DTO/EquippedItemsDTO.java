package com.example.WarriorsTest.models.DTO;

import com.example.WarriorsTest.models.entity.ItemEntity;

import java.util.ArrayList;
import java.util.List;

public class EquippedItemsDTO {

    private Long id;

    private ItemEntity weapon;

    private ItemEntity helmet;

    private ItemEntity chest;

    private ItemEntity boots;

    public EquippedItemsDTO() {
    }

    public Long getId() {
        return id;
    }

    public List<Long> getEquippedItemsIDs() {
        List<Long> list = new ArrayList<>();
        if (weapon != null) list.add(weapon.getId());
        if (helmet != null) list.add(helmet.getId());
        if (chest != null) list.add(chest.getId());
        if (boots != null) list.add(boots.getId());
        return list;
    }

    public List<ItemEntity> getEquippedItems() {
        List<ItemEntity> list = new ArrayList<>();
        if (weapon != null) list.add(weapon);
        if (helmet != null) list.add(helmet);
        if (chest != null) list.add(chest);
        if (boots != null) list.add(boots);
        return list;
    }

    public EquippedItemsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ItemEntity getWeapon() {
        return weapon;
    }

    public EquippedItemsDTO setWeapon(ItemEntity weapon) {
        this.weapon = weapon;
        return this;
    }

    public ItemEntity getHelmet() {
        return helmet;
    }

    public EquippedItemsDTO setHelmet(ItemEntity helmet) {
        this.helmet = helmet;
        return this;
    }

    public ItemEntity getChest() {
        return chest;
    }

    public EquippedItemsDTO setChest(ItemEntity chest) {
        this.chest = chest;
        return this;
    }

    public ItemEntity getBoots() {
        return boots;
    }

    public EquippedItemsDTO setBoots(ItemEntity boots) {
        this.boots = boots;
        return this;
    }
}
