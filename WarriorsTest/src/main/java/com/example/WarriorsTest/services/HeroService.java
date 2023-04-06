package com.example.WarriorsTest.services;

import com.example.WarriorsTest.models.entity.HeroEntity;
import com.example.WarriorsTest.repository.HeroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService {

    private final HeroRepository heroRepository;


    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public void saveAndFlush(HeroEntity hero) {
        heroRepository.saveAndFlush(hero);
    }

    public void save(HeroEntity hero) {
        heroRepository.save(hero);
    }


    public Optional<HeroEntity> getHeroByName(String name) {
        return heroRepository.findHeroEntityByName(name);
    }


    public Page<HeroEntity> findFirst100ByLevel() {
        return heroRepository.findAll(
                PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "level")));
    }

    public Optional<HeroEntity> findHeroById(Long id){
        return heroRepository.findHeroEntityById(id);
    }


}
