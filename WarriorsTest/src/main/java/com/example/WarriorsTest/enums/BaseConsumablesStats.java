package com.example.WarriorsTest.enums;

public enum BaseConsumablesStats {
    HEALTH_POTION(100, 0.200),
    MANA_POTION(200, 0.200);
    public final int stat;
    public final double weight;

    BaseConsumablesStats(int stat, double weight) {
        this.stat = stat;
        this.weight = weight;
    }
}