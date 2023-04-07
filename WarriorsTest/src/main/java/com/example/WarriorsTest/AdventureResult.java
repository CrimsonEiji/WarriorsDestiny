package com.example.WarriorsTest;

import com.example.WarriorsTest.models.DTO.HeroForBattleDTO;

import java.util.ArrayList;
import java.util.List;

public class AdventureResult {
    private HeroForBattleDTO hero;
    private boolean isDead;
    private List<Enemy> enemiesBeaten;
    private int rewards;
    private int goldFind;

    public AdventureResult(HeroForBattleDTO hero) {
        this.hero = hero;
        this.enemiesBeaten = new ArrayList<>();
        this.isDead = false;
        this.rewards = 0;
        this.goldFind = 0;
    }

    public boolean isDead() {
        return isDead;
    }

    public HeroForBattleDTO getHero() {
        return hero;
    }

    public AdventureResult setDead(boolean dead) {
        isDead = dead;
        return this;
    }
    public void addEnemy(Enemy enemy){
        enemiesBeaten.add(enemy);
    }

    public AdventureResult setHero(HeroForBattleDTO hero) {
        this.hero = hero;
        return this;
    }

    public List<Enemy> getEnemiesBeaten() {
        return enemiesBeaten;
    }

    public AdventureResult setEnemiesBeaten(List<Enemy> enemiesBeaten) {
        this.enemiesBeaten = enemiesBeaten;
        return this;
    }

    public int getRewards() {
        return rewards;
    }

    public AdventureResult setRewards(int rewards) {
        this.rewards = rewards;
        return this;
    }

    public int getGoldFind() {
        return goldFind;
    }

    public AdventureResult setGoldFind(int goldFind) {
        this.goldFind = goldFind;
        return this;
    }
}
