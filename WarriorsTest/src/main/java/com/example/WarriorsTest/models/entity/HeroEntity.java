package com.example.WarriorsTest.models.entity;

import com.example.WarriorsTest.enums.HeroClass;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "heroes")
public class HeroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private HeroClass heroClass;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private String name;

    @OneToMany
    private List<ItemEntity> inventory;

    @OneToMany
    private List<SpellEntity> spells;

    @OneToOne
    private StatsEntity stats;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public HeroEntity() {
    }

    public int getLevel() {
        return level;
    }

    public HeroEntity setLevel(int level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public HeroEntity setName(String name) {
        this.name = name;
        return this;
    }

    public List<ItemEntity> getInventory() {
        return inventory;
    }

    public HeroEntity setInventory(List<ItemEntity> inventory) {
        this.inventory = inventory;
        return this;
    }

    public List<SpellEntity> getSpells() {
        return spells;
    }

    public HeroEntity setSpells(List<SpellEntity> spells) {
        this.spells = spells;
        return this;
    }

    public StatsEntity getStats() {
        return stats;
    }

    public HeroEntity setStats(StatsEntity stats) {
        this.stats = stats;
        return this;
    }
}
