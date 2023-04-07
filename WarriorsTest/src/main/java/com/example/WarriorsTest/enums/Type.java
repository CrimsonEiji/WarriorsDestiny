package com.example.WarriorsTest.enums;

public enum Type {
    SWORD("Sword"),
    STAFF("Staff"),
    BOW("Bow"),
    HELMET("Helmet"),
    CHEST("Chest"),
    BOOTS("Boots"),
    HEALTH_POTION("Health potion"),
    MANA_POTION("Mana potion");

    public final String value;

    Type(String value) {
        this.value = value;
    }
}
