package com.example.WarriorsTest.validations.validators;

import com.example.WarriorsTest.exeptions.user_role_change.UserDoesntExistException;
import com.example.WarriorsTest.models.DTO.UserRoleRemoveDTO;
import com.example.WarriorsTest.models.entity.RoleEntity;
import com.example.WarriorsTest.models.entity.UserEntity;
import com.example.WarriorsTest.repository.RoleRepository;
import com.example.WarriorsTest.services.UserService;
import com.example.WarriorsTest.validations.anotations.RoleDoesntExistsValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class RoleDoesntExistsValidator implements ConstraintValidator<RoleDoesntExistsValidation, UserRoleRemoveDTO> {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public RoleDoesntExistsValidator(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void initialize(RoleDoesntExistsValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRoleRemoveDTO userRoleRemoveDTO, ConstraintValidatorContext context) {

        UserEntity user = userService.findByUsernameOptional(userRoleRemoveDTO.getUsername())
                .orElseThrow(() -> new UserDoesntExistException(userRoleRemoveDTO.getUsername(),false));
        RoleEntity role = roleRepository.findByUserRole(userRoleRemoveDTO.getUserRole())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        System.out.println(!user.getRoles().contains(role));
        if (user.getRoles().contains(role)) {
            return true;
        }


        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode("userRole")
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
