package com.example.WarriorsTest.services;

import com.example.WarriorsTest.models.entity.SpellEntity;
import com.example.WarriorsTest.models.entity.StatsEntity;
import com.example.WarriorsTest.repository.SpellsRepository;
import org.springframework.stereotype.Service;

@Service
public class SpellsService {

    private final SpellsRepository spellsRepository;


    public SpellsService(SpellsRepository spellsRepository) {
        this.spellsRepository = spellsRepository;
    }

    public void saveAndFlush(SpellEntity spell) {
        spellsRepository.saveAndFlush(spell);
    }

    public void save(SpellEntity spell) {
        spellsRepository.save(spell);
    }

}
