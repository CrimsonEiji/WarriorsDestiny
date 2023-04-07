package com.example.WarriorsTest.exeptions.inventory;


public class InventoryFullException extends RuntimeException {

    public InventoryFullException(String msg) {
        super(msg);
    }
}
