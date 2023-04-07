package com.example.WarriorsTest.services;

import com.example.WarriorsTest.models.DTO.EquippedItemsDTO;
import com.example.WarriorsTest.models.entity.EquippedItemsEntity;
import com.example.WarriorsTest.models.entity.ItemEntity;
import com.example.WarriorsTest.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public ItemService(ItemRepository itemRepository, UserService userService, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;

    }

    public ItemEntity findById(long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void saveAndFlush(ItemEntity item) {
        itemRepository.saveAndFlush(item);
    }

    public void save(ItemEntity item) {
        itemRepository.save(item);
    }

    public boolean isItemOnUser(String username, ItemEntity item) {
        return userService.findByUsername(username)
                .getHero().getInventory().contains(item);
    }

    public boolean isItemEquipped(long itemId, String username) {
        EquippedItemsEntity equippedItems = userService.findByUsername(username)
                .getHero().getEquipped();
        EquippedItemsDTO equippedItemsDTO = modelMapper.map(equippedItems, EquippedItemsDTO.class);

        return equippedItemsDTO.getEquippedItemsIDs().contains(itemId);
    }

    public void deleteItem(ItemEntity item){
        itemRepository.delete(item);
    }
}
