package com.example.WarriorsTest.services;

import com.example.WarriorsTest.models.entity.EquippedItemsEntity;
import com.example.WarriorsTest.repository.EquippedItemsRepository;
import org.springframework.stereotype.Service;

@Service
public class EquippedItemsService {

    private final EquippedItemsRepository repository;

    public EquippedItemsService(EquippedItemsRepository repository) {
        this.repository = repository;
    }

    public void save(EquippedItemsEntity item) {
        repository.save(item);
    }

    public void saveAndFlash(EquippedItemsEntity item) {
        repository.saveAndFlush(item);
    }
}
