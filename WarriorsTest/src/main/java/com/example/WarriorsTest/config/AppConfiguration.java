package com.example.WarriorsTest.config;

import com.example.WarriorsTest.models.DTO.ShopItemDTO;
import com.example.WarriorsTest.models.entity.ItemEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfiguration {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    @Scope("singleton")
    public List<ShopItemDTO> getGeneratedItemsShop(){
      return new ArrayList<>();
    }
}
