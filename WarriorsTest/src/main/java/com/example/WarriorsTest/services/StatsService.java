package com.example.WarriorsTest.services;


import com.example.WarriorsTest.models.entity.StatsEntity;
import com.example.WarriorsTest.repository.StatsRepository;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    private final StatsRepository statsRepository;


    public StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public void saveAndFlush(StatsEntity stats) {
        statsRepository.saveAndFlush(stats);
    }

    public void save(StatsEntity stats) {
        statsRepository.save(stats);
    }



}
