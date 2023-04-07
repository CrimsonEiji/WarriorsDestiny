package com.example.WarriorsTest.enums;

public enum Material {
    LEATHER("Leather"), PLATE("Plate"), STEAL("Steal"), WOOD("Wood"), LIQUID("Liquid");
    public final String value;

    Material(String value) {
        this.value = value;
    }
}
