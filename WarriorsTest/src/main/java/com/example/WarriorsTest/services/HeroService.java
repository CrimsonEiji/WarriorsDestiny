package com.example.WarriorsTest.services;

import com.example.WarriorsTest.models.entity.HeroEntity;
import com.example.WarriorsTest.repository.HeroRepository;
import org.springframework.stereotype.Service;

@Service
public class HeroService {

    private final HeroRepository heroRepository;


    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public void saveAndFlush(HeroEntity hero){
        heroRepository.saveAndFlush(hero);
    }
    public void save(HeroEntity hero){
        heroRepository.save(hero);
    }
}
