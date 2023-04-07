package com.example.WarriorsTest;

import com.example.WarriorsTest.enums.BaseEnemyStats;
import com.example.WarriorsTest.enums.Rarity;
import com.example.WarriorsTest.enums.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyGenerator {
    private final int level;
    private final int maxEnemies;
    private final Random random;

    public EnemyGenerator(int level) {
        this.level = level;
        this.random = new Random();

        this.maxEnemies = chooseEnemyCount(random.nextDouble(0, 100.00));
    }

    public List<Enemy> generate() {
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < maxEnemies; i++) {
            Enemy enemy = new Enemy(level);
            BaseEnemyStats stats = chooseStat(random.nextDouble(0, 100.00));
            enemy.setBaseStats(stats);
            enemies.add(enemy);
        }
        return enemies;
    }

    private int chooseEnemyCount(double chance){
        if (chance > 29.50) return 1;
        if (chance > 7.50) return 2;
        if (chance > 2.50) return 3;
        if (chance > 0.50) return 4;
        else return 7;
    }

    private BaseEnemyStats chooseStat(double chance) {
        if (chance > 66.66) {
            return BaseEnemyStats.ATTACKER;
        } else if (chance > 33.33) {
            return BaseEnemyStats.TANK;
        } else {
            return BaseEnemyStats.HYBRID;
        }
    }
}
