package com.example.WarriorsTest.models.entity;

import com.example.WarriorsTest.enums.UserRoles;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private UserRoles userRole;

    public RoleEntity() {
    }

    public UserRoles getUserRole() {
        return userRole;
    }

    public RoleEntity setUserRole(UserRoles userRole) {
        this.userRole = userRole;
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
