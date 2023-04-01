package com.example.WarriorsTest.enums;

public enum Rarity {
    LEGENDARY(1.5), MYTHIC(1.3), EPIC(1.2), RARE(1.1), COMMON(1);

    public final double multiplier;

    Rarity(double multiplier) {
        this.multiplier = multiplier;
    }
}
