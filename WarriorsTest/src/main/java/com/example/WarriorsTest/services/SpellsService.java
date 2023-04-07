package com.example.WarriorsTest.services;

import com.example.WarriorsTest.enums.HeroClass;
import com.example.WarriorsTest.models.entity.SpellEntity;
import com.example.WarriorsTest.models.entity.StatsEntity;
import com.example.WarriorsTest.repository.SpellsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    public List<SpellEntity> findSpellsByHeroClass(HeroClass heroClass) {
        return spellsRepository.findAllByForClass(heroClass);
    }

    public SpellEntity findSpellsByID(long id) {
        return spellsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
