package com.example.WarriorsTest.models.DTO;

import com.example.WarriorsTest.models.entity.RoleEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDTO {
    private String username;

    private String email;

    private List<RoleEntity> roles;

    public UserDTO() {
    }

    public String getRolesAsString() {
        List<String> rolesNames = new ArrayList<>();
        for (RoleEntity role : roles) {
            rolesNames.add(role.getUserRole().name());
        }
        if (rolesNames.isEmpty()) {
            return "User";
        }
        return String.join(", ", rolesNames);
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public UserDTO setRoles(List<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
