package com.example.WarriorsTest.validations.anotations;

import com.example.WarriorsTest.error_strings.ErrorStrings;
import com.example.WarriorsTest.validations.validators.RoleDoesntExistsValidator;
import com.example.WarriorsTest.validations.validators.UsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = RoleDoesntExistsValidator.class)
public @interface RoleDoesntExistsValidation {
    String message() default ErrorStrings.USER_DOESNT_HAVE_ROLE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
