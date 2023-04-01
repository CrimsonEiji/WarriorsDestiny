package com.example.WarriorsTest.enums;

public enum BaseArmourStats {
    HELMET(4, 1),
    CHEST(14, 4),
    BOOTS(6, 2);
    public final int stat;
    public final double weight;

    BaseArmourStats(int stat, double weight) {
        this.stat = stat;
        this.weight = weight;
    }
}
