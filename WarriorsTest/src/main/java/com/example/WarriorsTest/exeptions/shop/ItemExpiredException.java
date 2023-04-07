package com.example.WarriorsTest.exeptions.shop;

import com.example.WarriorsTest.error_strings.ErrorStrings;

public class ItemExpiredException extends RuntimeException implements ShopException {

    public ItemExpiredException() {
        super(ErrorStrings.ITEM_EXPIRED);
    }

}
