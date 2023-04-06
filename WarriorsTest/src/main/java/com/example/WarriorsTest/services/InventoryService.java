package com.example.WarriorsTest.services;


import com.example.WarriorsTest.models.DTO.UserItemDTO;
import com.example.WarriorsTest.models.entity.ItemEntity;
import com.example.WarriorsTest.models.entity.UserEntity;
import com.example.WarriorsTest.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public InventoryService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserItemDTO> getInventory(String username) {
        Optional<UserEntity> potentialUser = userRepository.findUserByUsername(username);

        return potentialUser.map(userEntity -> userEntity.getHero().getInventory().stream()
                        .map(item -> modelMapper.map(item, UserItemDTO.class)).toList())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN));
    }
}
