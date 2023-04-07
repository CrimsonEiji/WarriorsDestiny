package com.example.WarriorsTest.models.DTO;

import com.example.WarriorsTest.models.entity.SpellEntity;
import com.example.WarriorsTest.models.entity.StatsEntity;

public class HeroForBattleDTO {

    private int level;

    private String name;

    private SpellEntity spell;

    private StatsEntity stats;

    public HeroForBattleDTO() {
    }

    public int getLevel() {
        return level;
    }

    public HeroForBattleDTO setLevel(int level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public HeroForBattleDTO setName(String name) {
        this.name = name;
        return this;
    }

    public SpellEntity getSpell() {
        return spell;
    }

    public HeroForBattleDTO setSpell(SpellEntity spell) {
        this.spell = spell;
        return this;
    }

    public StatsEntity getStats() {
        return stats;
    }

    public HeroForBattleDTO setStats(StatsEntity stats) {
        this.stats = stats;
        return this;
    }
}
