package com.example.WarriorsTest.models.DTO;

import com.example.WarriorsTest.validations.EmailValidation;
import com.example.WarriorsTest.validations.PasswordMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@PasswordMatch
@EmailValidation
public class UserRegisterDTO {
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String confirmPassword;
    @Email(message = "Input should be a valid email!")
    @NotEmpty(message = "Email cannot be empty!")
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
