package com.example.WarriorsTest.validations;

import com.example.WarriorsTest.models.DTO.UserRegisterDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatcher implements ConstraintValidator<PasswordMatch, UserRegisterDTO> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterDTO userRegisterDTO, ConstraintValidatorContext context) {

        if (userRegisterDTO.getPassword() != null &&
        userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) return true;

        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode("confirmPassword")
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
