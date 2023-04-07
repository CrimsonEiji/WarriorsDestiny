package com.example.WarriorsTest.config;

import com.example.WarriorsTest.RandomItemGenerator;
import com.example.WarriorsTest.models.DTO.ShopItemDTO;
import com.example.WarriorsTest.models.entity.HeroEntity;
import com.example.WarriorsTest.services.HeroService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
import java.util.Random;

@Configuration
@EnableScheduling
public class Scheduled {


    private final HeroService heroService;
    private final ModelMapper modelMapper;
    private final List<ShopItemDTO> list;

    public Scheduled(HeroService heroService, ModelMapper modelMapper, List<ShopItemDTO> list) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
        this.list = list;
    }

    @org.springframework.scheduling.annotation.Scheduled(cron = "0 0 */12 * * *")
    public void giveGoldEvery12Hours() {
        Page<HeroEntity> heroes = heroService.getPageableUsersStep100(0);
        heroes.stream().forEach(hero -> {
            hero.setGold(hero.getGold() + 50);
        });
        heroService.saveAllAndFlush(heroes.getContent());
        for (int i = 1; i < heroes.getTotalPages(); i++) {
            Page<HeroEntity> nextHeroes = heroService.getPageableUsersStep100(i);
            nextHeroes.stream().forEach(hero -> {
                hero.setGold(hero.getGold() + 50);
            });
            heroService.saveAllAndFlush(nextHeroes.getContent());
        }
    }

    @org.springframework.scheduling.annotation.Scheduled(cron = "10/10 * * * * *")
    public void refreshShop() {
        list.clear();

        RandomItemGenerator randomItemGenerator = new RandomItemGenerator();
        for (int i = 0; i < 40; i++) {
            Random random = new Random();
            ShopItemDTO shopItemDTO = modelMapper
                    .map(randomItemGenerator.generate(random.nextInt(0, 100)), ShopItemDTO.class);

            list.add(shopItemDTO);
        }
    }
}
