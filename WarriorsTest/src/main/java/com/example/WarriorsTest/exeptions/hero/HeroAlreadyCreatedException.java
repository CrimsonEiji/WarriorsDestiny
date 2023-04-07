package com.example.WarriorsTest.exeptions.hero;

import com.example.WarriorsTest.error_strings.ErrorStrings;

public class HeroAlreadyCreatedException extends RuntimeException{

    public HeroAlreadyCreatedException() {
    super(ErrorStrings.HERO_ALREADY_CREATED_EXCEPTION);
    }
}
