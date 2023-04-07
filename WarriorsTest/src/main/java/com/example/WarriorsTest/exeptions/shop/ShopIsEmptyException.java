package com.example.WarriorsTest.exeptions.shop;

import com.example.WarriorsTest.error_strings.ErrorStrings;

public class ShopIsEmptyException extends RuntimeException implements ShopException {

    public ShopIsEmptyException() {
        super(ErrorStrings.SHOP_EMPTY);
    }

}
