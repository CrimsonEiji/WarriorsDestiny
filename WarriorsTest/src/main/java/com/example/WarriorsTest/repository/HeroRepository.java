package com.example.WarriorsTest.repository;

import com.example.WarriorsTest.models.entity.HeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<HeroEntity,Long> {

    Optional<HeroEntity> findHeroEntityByName(String name);

    Optional<HeroEntity> findHeroEntityById(Long id);

}
