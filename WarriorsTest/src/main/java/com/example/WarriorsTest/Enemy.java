package com.example.WarriorsTest;

import com.example.WarriorsTest.enums.BaseEnemyStats;


public class Enemy {

    private BaseEnemyStats baseStats;

    private int level;

    private String name;

    private int attack;

    private int hitsNeededToDie;

    public Enemy(int level) {
        this.level = level;
    }

    public BaseEnemyStats getBaseStats() {
        return baseStats;
    }

    public Enemy setBaseStats(BaseEnemyStats baseStats) {
        this.baseStats = baseStats;

        attack = baseStats.attack;
        hitsNeededToDie = baseStats.hitsNeeded;
        name = baseStats.name;

        return this;
    }

    public int getLevel() {
        return level;
    }

    public Enemy setLevel(int level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public Enemy setName(String name) {
        this.name = name;
        return this;
    }

    public int getAttack() {
        return attack;
    }

    public Enemy setAttack(int attack) {
        this.attack = attack;
        return this;
    }

    public int getHitsNeededToDie() {
        return hitsNeededToDie;
    }

    public Enemy setHitsNeededToDie(int hitsNeededToDie) {
        this.hitsNeededToDie = hitsNeededToDie;
        return this;
    }
}
