package com.example.WarriorsTest.services;

import com.example.WarriorsTest.enums.UserRoles;
import com.example.WarriorsTest.models.entity.RoleEntity;
import com.example.WarriorsTest.models.entity.UserEntity;
import com.example.WarriorsTest.repository.RoleRepository;
import com.example.WarriorsTest.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InitService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void initBase() {
        initRoles();
        initUsers();
    }

    private void initRoles() {
        if (roleRepository.count() == 0) {
            var adminRole = new RoleEntity().setUserRole(UserRoles.ADMIN);
            var moderatorRole = new RoleEntity().setUserRole(UserRoles.MODERATOR);
            roleRepository.save(adminRole);
            roleRepository.save(moderatorRole);
        }
    }

    private void initUsers() {
        if (userRepository.count() < 0) {
            var user1 = new UserEntity()
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("admin"))
                    .setEmail("admin@admin.com")
                    .setRoles(roleRepository.findAll());

            userRepository.save(user1);
        }
    }


}
