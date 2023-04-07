package com.example.WarriorsTest.services;

import com.example.WarriorsTest.enums.UserRoles;
import com.example.WarriorsTest.models.entity.RoleEntity;
import com.example.WarriorsTest.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleEntity findByName(UserRoles userRole) {
        return roleRepository.findByUserRole(userRole)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
