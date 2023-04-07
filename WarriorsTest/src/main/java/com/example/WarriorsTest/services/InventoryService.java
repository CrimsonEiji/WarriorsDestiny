package com.example.WarriorsTest.services;


import com.example.WarriorsTest.models.DTO.UserItemDTO;
import com.example.WarriorsTest.models.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {


    private final UserService userService;
    private final ModelMapper modelMapper;

    public InventoryService(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public List<UserItemDTO> getInventory(String username) {
        UserEntity potentialUser = userService.findByUsername(username);

        return potentialUser.getHero().getInventory().stream()
                .map(item -> modelMapper.map(item, UserItemDTO.class)).toList();
    }
}
