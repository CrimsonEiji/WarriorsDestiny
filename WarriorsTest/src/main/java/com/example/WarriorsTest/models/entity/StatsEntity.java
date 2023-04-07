package com.example.WarriorsTest.models.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "stats")
public class StatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer health;

    @Column
    private Integer mana;

    @Column
    private Integer currentHealth;

    @Column
    private Integer currentMana;

    @Column
    private Integer attack;

    @Column
    private Integer armour;

    @Column
    private Integer strength;

    @Column
    private Integer intellect;

    @Column
    private Integer agility;

    @Column
    private Double dodgeChance;


    public StatsEntity() {
    }

    public Integer getCurrentHealth() {
        return currentHealth;
    }

    public StatsEntity setCurrentHealth(Integer currentHealth) {
        this.currentHealth = currentHealth;
        return this;
    }

    public Integer getCurrentMana() {
        return currentMana;
    }

    public StatsEntity setCurrentMana(Integer currentMana) {
        this.currentMana = currentMana;
        return this;
    }

    public Long getId() {
        return id;
    }

    public StatsEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getAttack() {
        return attack;
    }

    public StatsEntity setAttack(Integer physicalAttack) {
        this.attack = physicalAttack;
        return this;
    }

    public Integer getArmour() {
        return armour;
    }

    public StatsEntity setArmour(Integer spellAttack) {
        this.armour = spellAttack;
        return this;
    }

    public Integer getHealth() {
        return health;
    }

    public StatsEntity setHealth(Integer health) {
        this.health = health;
        return this;
    }

    public Integer getMana() {
        return mana;
    }

    public StatsEntity setMana(Integer mana) {
        this.mana = mana;
        return this;
    }

    public Integer getStrength() {
        return strength;
    }

    public StatsEntity setStrength(Integer strength) {
        this.strength = strength;
        return this;
    }

    public Integer getIntellect() {
        return intellect;
    }

    public StatsEntity setIntellect(Integer intellect) {
        this.intellect = intellect;
        return this;
    }

    public Integer getAgility() {
        return agility;
    }

    public StatsEntity setAgility(Integer agility) {
        this.agility = agility;
        return this;
    }

    public Double getDodgeChance() {
        return dodgeChance;
    }

    public StatsEntity setDodgeChance(Double dodgeChance) {
        this.dodgeChance = dodgeChance;
        return this;
    }
}
