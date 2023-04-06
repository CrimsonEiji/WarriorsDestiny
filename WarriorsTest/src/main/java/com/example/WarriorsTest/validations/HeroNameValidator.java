package com.example.WarriorsTest.validations;

import com.example.WarriorsTest.models.DTO.HeroCreationDTO;
import com.example.WarriorsTest.models.DTO.UserRegisterDTO;
import com.example.WarriorsTest.services.HeroService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HeroNameValidator implements ConstraintValidator<HeroNameValidation, HeroCreationDTO> {

    private final HeroService heroService;

    public HeroNameValidator(HeroService heroService) {
        this.heroService = heroService;
    }

    @Override
    public void initialize(HeroNameValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(HeroCreationDTO heroCreationDTO, ConstraintValidatorContext context) {


        if (heroService.getHeroByName(heroCreationDTO.getName()).isEmpty()) return true;

        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode("name")
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
