package com.example.WarriorsTest.exeptions.shop;

import com.example.WarriorsTest.error_strings.ErrorStrings;

public class NotEnoughGoldException extends RuntimeException {
    private final int id;
    private final String itemObjectName;
    public NotEnoughGoldException(int id,String itemObjectName) {
        super(ErrorStrings.NOT_ENOUGH_GOLD);
        this.id = id;
        this.itemObjectName = itemObjectName;
    }

    public int getId() {
        return id;
    }

    public String getItemObjectName() {
        return itemObjectName;
    }
}
