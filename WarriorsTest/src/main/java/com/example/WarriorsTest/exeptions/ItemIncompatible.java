package com.example.WarriorsTest.exeptions;

public class ItemIncompatible extends RuntimeException{
    private final long id;

    public ItemIncompatible(String msg,long id) {
    super(msg);
    this.id  = id;
    }

    public long getId() {
        return id;
    }
}
