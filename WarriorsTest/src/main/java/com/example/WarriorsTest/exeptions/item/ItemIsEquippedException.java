package com.example.WarriorsTest.exeptions.item;

import com.example.WarriorsTest.error_strings.ErrorStrings;

public class ItemIsEquippedException extends RuntimeException implements ItemException{
    private final long id;

    public ItemIsEquippedException( long id) {
    super(ErrorStrings.ITEM_IS_EQUIPPED_EXCEPTION);
    this.id  = id;
    }

    public long getId() {
        return id;
    }
}
