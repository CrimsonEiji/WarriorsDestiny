package com.example.WarriorsTest.repository;

import com.example.WarriorsTest.models.entity.StatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepository extends JpaRepository<StatsEntity,Long> {

}
