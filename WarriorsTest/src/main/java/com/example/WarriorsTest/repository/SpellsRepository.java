package com.example.WarriorsTest.repository;

import com.example.WarriorsTest.models.entity.SpellEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellsRepository extends JpaRepository<SpellEntity, Long> {
}
