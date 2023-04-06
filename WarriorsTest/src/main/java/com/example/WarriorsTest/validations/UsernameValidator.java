package com.example.WarriorsTest.validations;

import com.example.WarriorsTest.models.DTO.UserRegisterDTO;
import com.example.WarriorsTest.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<UsernameValidation, UserRegisterDTO> {

    private final UserService userService;

    public UsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UsernameValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterDTO userRegisterDTO, ConstraintValidatorContext context) {


        if (userService.findByUsername(userRegisterDTO.getUsername()).isEmpty()) return true;

        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode("username")
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
