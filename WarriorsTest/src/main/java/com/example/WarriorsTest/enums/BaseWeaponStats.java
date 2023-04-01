package com.example.WarriorsTest.enums;

public enum BaseWeaponStats {
    SWORD(7, 1),
    STAFF(10, 1.5),
    BOW(6, 0.6);
    public final int stat;
    public final double weight;

    BaseWeaponStats(int stat, double weight) {
        this.stat = stat;
        this.weight = weight;
    }
}