package com.example.WarriorsTest.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = EmailValidator.class)
public @interface EmailValidation {
    String message() default "Email already exist!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
