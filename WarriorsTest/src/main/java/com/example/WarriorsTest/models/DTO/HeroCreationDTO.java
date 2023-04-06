package com.example.WarriorsTest.models.DTO;

import com.example.WarriorsTest.enums.HeroClass;
import com.example.WarriorsTest.validations.HeroNameValidation;
import jakarta.validation.constraints.Size;

@HeroNameValidation
public class HeroCreationDTO {

    private HeroClass heroClass;

    @Size(min = 3,max = 20)
    private String name;

    public HeroCreationDTO() {
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public HeroCreationDTO setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
        return this;
    }

    public String getName() {
        return name;
    }

    public HeroCreationDTO setName(String name) {
        this.name = name;
        return this;
    }
}
