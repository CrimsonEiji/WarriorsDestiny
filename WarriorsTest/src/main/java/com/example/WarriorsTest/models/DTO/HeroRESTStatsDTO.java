package com.example.WarriorsTest.models.DTO;

import com.example.WarriorsTest.enums.HeroClass;
import com.example.WarriorsTest.models.entity.StatsEntity;

public class HeroRESTStatsDTO {
    private int level;

    private HeroClass heroClass;

    private int experience;

    private int experienceNeededToLevelUp;

    private String name;

    private Integer gold;

    private StatsEntity stats;

    public HeroRESTStatsDTO() {
    }

    public int getLevel() {
        return level;
    }

    public HeroRESTStatsDTO setLevel(int level) {
        this.level = level;
        return this;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public HeroRESTStatsDTO setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
        return this;
    }

    public int getExperience() {
        return experience;
    }

    public HeroRESTStatsDTO setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    public int getExperienceNeededToLevelUp() {
        return experienceNeededToLevelUp;
    }

    public HeroRESTStatsDTO setExperienceNeededToLevelUp(int experienceNeededToLevelUp) {
        this.experienceNeededToLevelUp = experienceNeededToLevelUp;
        return this;
    }

    public String getName() {
        return name;
    }

    public HeroRESTStatsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getGold() {
        return gold;
    }

    public HeroRESTStatsDTO setGold(Integer gold) {
        this.gold = gold;
        return this;
    }

    public StatsEntity getStats() {
        return stats;
    }

    public HeroRESTStatsDTO setStats(StatsEntity stats) {
        this.stats = stats;
        return this;
    }
}
