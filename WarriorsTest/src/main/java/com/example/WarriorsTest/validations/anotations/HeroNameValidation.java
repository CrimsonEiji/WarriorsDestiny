package com.example.WarriorsTest.validations.anotations;

import com.example.WarriorsTest.error_strings.ErrorStrings;
import com.example.WarriorsTest.validations.validators.HeroNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = HeroNameValidator.class)
public @interface HeroNameValidation {
    String message() default ErrorStrings.NAME_ALREADY_EXIST;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
