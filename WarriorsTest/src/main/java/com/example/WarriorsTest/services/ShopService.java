package com.example.WarriorsTest.services;

import com.example.WarriorsTest.models.DTO.ShopItemDTO;
import com.example.WarriorsTest.models.entity.ItemEntity;
import com.example.WarriorsTest.models.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private final List<ShopItemDTO> list;
    private final ItemService itemService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public ShopService(List<ShopItemDTO> list, ItemService itemService, ModelMapper modelMapper, UserService userService) {
        this.list = list;
        this.itemService = itemService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    public void buyItem(int id, String username) {
        UserEntity userEntity = userService.findByUsername(username);

        ShopItemDTO shopItemDTO = list.get(id);
        ItemEntity itemEntity = modelMapper.map(shopItemDTO, ItemEntity.class);

        itemService.saveAndFlush(itemEntity);

        userEntity.getHero().getInventory().add(itemEntity);
        userService.saveAndFlush(userEntity);

        list.remove(shopItemDTO);
    }
}
