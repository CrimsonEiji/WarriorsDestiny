package com.example.WarriorsTest.enums;

public enum BaseEnemyStats {
    TANK(5,3,"TANKER"),
    ATTACKER(10,1,"Hard hitter"),
    HYBRID(7,2,"Some guy");

    public final int attack;
    public final int hitsNeeded;
    public final String name;

    BaseEnemyStats(int attack,int hitsNeeded,String name) {
        this.attack = attack;
        this.hitsNeeded = hitsNeeded;
        this.name = name;
    }
}
