package com.example.WarriorsTest;

import com.example.WarriorsTest.models.DTO.HeroForBattleDTO;
import com.example.WarriorsTest.models.entity.SpellEntity;
import com.example.WarriorsTest.models.entity.StatsEntity;

import java.util.List;
import java.util.Random;

public class Adventure {

    public AdventureResult StartAdventure(HeroForBattleDTO hero) {
        AdventureResult adventureResult = new AdventureResult(hero);
        EnemyGenerator enemyGenerator = new EnemyGenerator(hero.getLevel());
        Random random = new Random();
        List<Enemy> enemies = enemyGenerator.generate();
        StatsEntity stat = hero.getStats();
        SpellEntity spell = hero.getSpell();
        for (Enemy enemy : enemies) {
            while (enemy.getHitsNeededToDie() > 0) {

                int lostHealth = enemy.getAttack() - (stat.getArmour() / 10);
                if (lostHealth < 0) lostHealth = 0;
                if (stat.getDodgeChance() < random.nextDouble(0, 100.00)) {
                    stat.setCurrentHealth(stat.getCurrentHealth()-lostHealth);
                }
                if (hero.getStats().getCurrentHealth() <= 0) {
                    return adventureResult.setDead(true); //GAME OVER
                }
                int hitEnemyFor = enemy.getHitsNeededToDie() - 1;

                if (spell != null && random.nextDouble(0, 100.00) > 66.66) {
                    if (stat.getCurrentMana() >= spell.getManaConsumption()) {
                        stat.setCurrentMana(stat.getCurrentMana() - spell.getManaConsumption());
                        hitEnemyFor = enemy.getHitsNeededToDie();
                    }
                }
                hero.setStats(stat);
                enemy.setHitsNeededToDie(hitEnemyFor);

                double chance = random.nextDouble(0, 100.00);
                if (chance > 10.00) {
                    adventureResult.setGoldFind(adventureResult.getGoldFind() + 10);
                } else if (chance > 5.00) {
                    adventureResult.setRewards(adventureResult.getRewards() + 1);
                } else {
                    adventureResult.setGoldFind(adventureResult.getGoldFind() + 50);
                }
                adventureResult.addEnemy(enemy);
            }
        }
        adventureResult.setHero(hero);

        return adventureResult;
    }


}
