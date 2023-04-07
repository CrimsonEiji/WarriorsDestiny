package com.example.WarriorsTest.exeptions.battle;

import com.example.WarriorsTest.error_strings.ErrorStrings;

public class HeroWasNotInBattleException extends RuntimeException {

    public HeroWasNotInBattleException() {
        super(ErrorStrings.HERO_WAS_NOT_IN_BATTLE);
    }
}
