package com.example.WarriorsTest.models.DTO;

import com.example.WarriorsTest.enums.HeroClass;
import com.example.WarriorsTest.models.entity.ItemEntity;
import com.example.WarriorsTest.models.entity.SpellEntity;
import com.example.WarriorsTest.models.entity.StatsEntity;

import java.util.List;

public class HeroDTO {
    private HeroClass heroClass;

    private int level;

    private String name;

    private List<ItemEntity> inventory;

    private List<SpellEntity> spells;

    private StatsEntity stats;

    public HeroDTO() {
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public HeroDTO setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public HeroDTO setLevel(int level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public HeroDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<ItemEntity> getInventory() {
        return inventory;
    }

    public HeroDTO setInventory(List<ItemEntity> inventory) {
        this.inventory = inventory;
        return this;
    }

    public List<SpellEntity> getSpells() {
        return spells;
    }

    public HeroDTO setSpells(List<SpellEntity> spells) {
        this.spells = spells;
        return this;
    }

    public StatsEntity getStats() {
        return stats;
    }

    public HeroDTO setStats(StatsEntity stats) {
        this.stats = stats;
        return this;
    }
}
