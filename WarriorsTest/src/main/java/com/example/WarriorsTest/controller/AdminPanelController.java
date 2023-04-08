package com.example.WarriorsTest.controller;


import com.example.WarriorsTest.exeptions.user_role_change.UserIsDefaultAdminException;
import com.example.WarriorsTest.models.DTO.UserDTO;
import com.example.WarriorsTest.models.DTO.UserRoleAddDTO;
import com.example.WarriorsTest.models.DTO.UserRoleRemoveDTO;
import com.example.WarriorsTest.models.entity.UserEntity;
import com.example.WarriorsTest.services.RoleService;
import com.example.WarriorsTest.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPanelController {
    private final UserService userService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    public AdminPanelController(UserService userService, RoleService roleService, ModelMapper modelMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    public String getAdminPanel() {
        return "AdminPanel";
    }

    @GetMapping("/users/{page}")
    public String getAdminUsers(@PathVariable int page, Model model) {
        Page<UserEntity> usersPaged = userService.findFirst100ByLevel(page);
        List<UserDTO> users = usersPaged.getContent().stream()
                .map(userEntity -> modelMapper.map(userEntity, UserDTO.class)).toList();

        model.addAttribute("currentPage", usersPaged.getPageable().getPageNumber());
        model.addAttribute("isPrevSeen", usersPaged.getPageable().getPageNumber() > 0);
        model.addAttribute("isNextSeen", usersPaged.getPageable().getPageNumber() < usersPaged.getTotalPages());
        model.addAttribute("users", users);

        return "AdminUsers";
    }


    @PostMapping("/addUserRole")
    public String addUserRole(@Valid @ModelAttribute UserRoleAddDTO userRoleAddDTO, BindingResult bindingResult,
                              RedirectAttributes ra, Principal principal) {

        if (bindingResult.hasErrors()) {
            ra.addFlashAttribute("userRoleAdd", userRoleAddDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRoleAdd", bindingResult);
        }
        if (userService.findByUsername(principal.getName()).getRoles().size() == 0) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        UserEntity userEntity = userService.findByUsername(userRoleAddDTO.getUsername());

        userEntity.getRoles().add(roleService.findByName(userRoleAddDTO.getUserRole()));
        userService.saveAndFlush(userEntity);

        return "redirect:/admin/changeUserRoles";
    }

    @PostMapping("/removeUserRole")
    public String removeUserRole(@Valid @ModelAttribute UserRoleRemoveDTO userRoleRemoveDTO, BindingResult bindingResult,
                                 RedirectAttributes ra,Principal principal) {
        if (userRoleRemoveDTO.getUsername().equals("admin")) {
            throw new UserIsDefaultAdminException(false);
        }
        if (userService.findByUsername(principal.getName()).getRoles().size() == 0) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        if (bindingResult.hasErrors()) {
            ra.addFlashAttribute("userRoleRemove", userRoleRemoveDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRoleRemove", bindingResult);
        }

        UserEntity userEntity = userService.findByUsername(userRoleRemoveDTO.getUsername());

        userEntity.getRoles().remove(roleService.findByName(userRoleRemoveDTO.getUserRole()));
        userService.saveAndFlush(userEntity);
        return "redirect:/admin/changeUserRoles";
    }

    @GetMapping("/changeUserRoles")
    public String getChangeUserRoles() {

        return "ChangeUserRoles";
    }


    @ModelAttribute("userRoleAdd")
    public UserRoleAddDTO UserRoleAddDTO() {
        return new UserRoleAddDTO();
    }

    @ModelAttribute("userRoleRemove")
    public UserRoleRemoveDTO UserRoleRemoveDTO() {
        return new UserRoleRemoveDTO();
    }
}
