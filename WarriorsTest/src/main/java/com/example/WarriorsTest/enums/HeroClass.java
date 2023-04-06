package com.example.WarriorsTest.enums;

public enum HeroClass {
    KNIGHT(200, 100, 50, 50, 6, 2, 2),
    MAGE(160, 200, 70, 30, 0, 8, 2),
    HUNTER(140, 140, 70, 40, 4, 0, 6);
    public final int health, mana, attack, armour, strength, intellect, agility;

    HeroClass(int health, int mana, int attack, int armour, int strength, int intellect, int agility) {
        this.health = health;
        this.mana = mana;
        this.attack = attack;
        this.armour = armour;
        this.strength = strength;
        this.intellect = intellect;
        this.agility = agility;
    }
}
