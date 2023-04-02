package com.example.WarriorsTest.services;

import com.example.WarriorsTest.models.entity.ItemEntity;
import com.example.WarriorsTest.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Optional<ItemEntity> findById(long id){
        return itemRepository.findById(id);
    }

    public void saveAndFlush(ItemEntity item) {
        itemRepository.saveAndFlush(item);
    }

    public void save(ItemEntity item) {
        itemRepository.save(item);
    }
}
