package com.example.WarriorsTest.validations.validators;

import com.example.WarriorsTest.models.DTO.UserRegisterDTO;
import com.example.WarriorsTest.services.UserService;
import com.example.WarriorsTest.validations.anotations.EmailValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidation, UserRegisterDTO> {

    private final UserService userService;

    public EmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(EmailValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterDTO userRegisterDTO, ConstraintValidatorContext context) {


        if (userService.findByEmailOptional(userRegisterDTO.getEmail()).isEmpty()) return true;

        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode("email")
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
