package com.example.WarriorsTest.repository;

import com.example.WarriorsTest.enums.HeroClass;
import com.example.WarriorsTest.models.entity.SpellEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpellsRepository extends JpaRepository<SpellEntity, Long> {

    List<SpellEntity> findAllByForClass(HeroClass heroClass);
    Optional<SpellEntity> findById(long id);
}
