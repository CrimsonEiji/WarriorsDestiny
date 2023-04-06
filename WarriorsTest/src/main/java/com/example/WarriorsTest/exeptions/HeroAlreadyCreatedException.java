package com.example.WarriorsTest.exeptions;

public class HeroAlreadyCreatedException extends RuntimeException{

    public HeroAlreadyCreatedException(String msg) {
    super(msg);
    }
}
