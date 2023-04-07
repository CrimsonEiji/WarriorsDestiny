package com.example.WarriorsTest.models.DTO;

import com.example.WarriorsTest.enums.UserRoles;
import com.example.WarriorsTest.validations.anotations.RoleDoesntExistsValidation;
import jakarta.validation.constraints.Size;

@RoleDoesntExistsValidation
public class UserRoleRemoveDTO {
    @Size(min =7)
    private String username;

    private UserRoles userRole;

    public UserRoleRemoveDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserRoleRemoveDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserRoles getUserRole() {
        return userRole;
    }

    public UserRoleRemoveDTO setUserRole(UserRoles userRole) {
        this.userRole = userRole;
        return this;
    }
}
