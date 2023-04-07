package com.example.WarriorsTest.enums;

public enum Element {
    WIND("Wind"),FIRE("Fire"),WATER("Water"),EARTH("Earth"),LIGHTNING("Lightning"),NONE("");
    public final String value;

    Element(String value) {
        this.value = value;
    }
}
