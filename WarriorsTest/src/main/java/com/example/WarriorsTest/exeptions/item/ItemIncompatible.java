package com.example.WarriorsTest.exeptions.item;

import com.example.WarriorsTest.enums.HeroClass;
import com.example.WarriorsTest.enums.Type;
import com.example.WarriorsTest.error_strings.ErrorStrings;

public class ItemIncompatible extends RuntimeException implements ItemException {
    private final long id;

    public ItemIncompatible(HeroClass heroClass, Type type, long id) {
        super(String.format(ErrorStrings.ITEM_INCOMPATIBLE_EXCEPTION, heroClass.name(), type.name()));
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
