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
    private int experience;

    @Column(nullable = false)
    private int experienceNeededToLevelUp;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer gold;

    @OneToMany
    private List<ItemEntity> inventory;

    @OneToOne
    private SpellEntity spell;

    @OneToOne
    private EquippedItemsEntity equipped;
    @OneToOne
    private StatsEntity stats;

    @OneToOne
    public EquippedItemsEntity getEquipped() {
        return equipped;
    }


    public HeroEntity updateExperience(int experience) {
        this.experience += experience;
        while (this.experience >= experienceNeededToLevelUp) {
            this.experience -= experienceNeededToLevelUp;
            experienceNeededToLevelUp += 20;
            this.level += 1;
            levelUp();
        }
        return this;
    }

    public int getExperienceNeededToLevelUp() {
        return experienceNeededToLevelUp;
    }

    public HeroEntity setExperienceNeededToLevelUp(int experienceNeededToLevelUp) {
        this.experienceNeededToLevelUp = experienceNeededToLevelUp;
        return this;
    }

    public void levelUp() {
        stats
                .setHealth(stats.getHealth() + 10)
                .setMana(stats.getMana() + 10)
                .setCurrentHealth(stats.getHealth())
                .setCurrentMana(stats.getMana())
                .setAttack(stats.getAttack() + 1)
                .setArmour(stats.getArmour() + 1);

    }

    public HeroEntity setEquipped(EquippedItemsEntity equipped) {
        this.equipped = equipped;
        return this;
    }


    public int getExperience() {
        return experience;
    }

    public HeroEntity setExperience(int experience) {
        this.experience = experience;
        return this;
    }


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

    public SpellEntity getSpell() {
        return spell;
    }

    public HeroEntity setSpell(SpellEntity spells) {
        this.spell = spells;
        return this;
    }

    public StatsEntity getStats() {
        return stats;
    }

    public HeroEntity setStats(StatsEntity stats) {
        this.stats = stats;
        return this;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public HeroEntity setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
        return this;
    }

    public Integer getGold() {
        return gold;
    }

    public HeroEntity setGold(Integer gold) {
        this.gold = gold;
        return this;
    }
}
