package com.example.WarriorsTest.enums;

public enum BaseHeroStat {
    KNIGHT(200, 100, 70, 50, 6, 2, 2),
    MAGE(160, 200, 40, 70, 0, 8, 2),
    HUNTER(140, 140, 30, 70, 4, 0, 6);
    public final int health, mana, physicalAttack, spellAttack, strength, intellect, agility;

    BaseHeroStat(int health, int mana, int physicalAttack, int spellAttack, int strength, int intellect, int agility) {
        this.health = health;
        this.mana = mana;
        this.physicalAttack = physicalAttack;
        this.spellAttack = spellAttack;
        this.strength = strength;
        this.intellect = intellect;
        this.agility = agility;
    }
}
