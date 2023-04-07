package com.example.WarriorsTest.models.DTO;

import com.example.WarriorsTest.enums.UserRoles;
import com.example.WarriorsTest.validations.anotations.RoleExistsValidation;

@RoleExistsValidation
public class UserRoleAddDTO {

    private String username;

    private UserRoles userRole;

    public UserRoleAddDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserRoleAddDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserRoles getUserRole() {
        return userRole;
    }

    public UserRoleAddDTO setUserRole(UserRoles userRole) {
        this.userRole = userRole;
        return this;
    }
}
