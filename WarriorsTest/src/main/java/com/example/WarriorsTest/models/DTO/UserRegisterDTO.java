package com.example.WarriorsTest.models.DTO;

import com.example.WarriorsTest.error_strings.ErrorStrings;
import com.example.WarriorsTest.validations.anotations.EmailValidation;
import com.example.WarriorsTest.validations.anotations.PasswordMatch;
import com.example.WarriorsTest.validations.anotations.UsernameValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@PasswordMatch
@EmailValidation
@UsernameValidation
public class UserRegisterDTO {
    @Size(min = 3, max = 20, message = ErrorStrings.USERNAME_LENGTH_ERROR)
    private String username;
    @Size(min = 3, max = 20, message = ErrorStrings.PASSWORD_LENGTH_ERROR)
    private String password;
    @Size(min = 3, max = 20, message = ErrorStrings.PASSWORD_LENGTH_ERROR)
    private String confirmPassword;
    @Email(message = ErrorStrings.INVALID_EMAIL)
    @NotEmpty(message = ErrorStrings.EMAIL_EMPTY)
    private String email;

    public UserRegisterDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
