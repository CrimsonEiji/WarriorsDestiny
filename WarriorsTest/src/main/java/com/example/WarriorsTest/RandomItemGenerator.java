package com.example.WarriorsTest;

import com.example.WarriorsTest.enums.*;
import com.example.WarriorsTest.models.entity.ItemEntity;

import java.util.Random;

public class RandomItemGenerator {


    public ItemEntity generate() {
        Random chance = new Random();
        ItemType itemType = getRandomItemType(chance.nextDouble(0, 100.0));
        Type type = getRandmType(itemType, chance.nextDouble(0, 100.0));
        Element element = getRandomElement(itemType, chance.nextDouble(0, 100.0));
        Rarity rarity = getRandomRarity(chance.nextDouble(0, 100.0));
        Material material = getRandomMaterial(chance.nextDouble(0, 100.0), itemType, type);
        int stat = getStat(rarity, type, material);
        double weight = getWeight(type, material);
        int durability = getDurability(rarity);
        String name = generateName(rarity, type, element);
        String description = generateDescription(type, element);
        return new ItemEntity()
                .setItemType(itemType)
                .setType(type)
                .setDescription(description)
                .setElement(element)
                .setName(name)
                .setDurability(durability)
                .setWeight(weight)
                .setStat(stat)
                .setRarity(rarity)
                .setMaterial(material);
    }

    private String generateName(Rarity rarity, Type type, Element element) {
        String format = "%s %s of %s";
        if (element == Element.NONE)
            return String.format(format, rarity.name(), type.name(), "").replace("of","");


        return String.format(format, rarity.name(), type.name(), element.name());
    }

    private String generateDescription(Type type, Element element) {
        String format = "%s made of %s element";
        if (element == Element.NONE){
            return String.format("%s", type.name());
        }
        return String.format(format, type.name(), element.name());
    }

    private ItemType getRandomItemType(double chance) {
        if (chance > 66.66) {
            return ItemType.CONSUMABLE;
        } else if (chance > 33.33) {
            return ItemType.ARMOR;
        } else {
            return ItemType.WEAPON;
        }
    }

//    private String getRandomDescription(){
//
//    }

    private Material getRandomMaterial(double chance, ItemType itemType, Type type) {
        if (itemType == ItemType.WEAPON && type == Type.SWORD) return Material.STEAL;
        else if (itemType == ItemType.WEAPON) return Material.WOOD;
        else if (itemType == ItemType.CONSUMABLE) return Material.LIQUID;
        else {
            if (chance > 50) {
                return Material.LEATHER;
            } else return Material.PLATE;
        }
    }

    private int getDurability(Rarity rarity) {
        int baseDurability = 50;
        baseDurability += (baseDurability * rarity.multiplier);
        return baseDurability;
    }

    private int getStat(Rarity rarity, Type type, Material material) {
        int stat = 0;
        switch (type) {
            case BOW -> stat = BaseWeaponStats.BOW.stat;
            case SWORD -> stat = BaseWeaponStats.SWORD.stat;
            case STAFF -> stat = BaseWeaponStats.STAFF.stat;
            case HELMET -> stat = BaseArmourStats.HELMET.stat;
            case CHEST -> stat = BaseArmourStats.CHEST.stat;
            case BOOTS -> stat = BaseArmourStats.BOOTS.stat;
            case MANA_POTION -> stat = BaseConsumablesStats.MANA_POTION.stat;
            case HEALTH_POTION -> stat = BaseConsumablesStats.HEALTH_POTION.stat;
        }
        stat *= rarity.multiplier;
        if (material == Material.PLATE) {
            stat *= 2;
        }
        return stat;
    }

    private double getWeight(Type type, Material material) {
        double weight = 0;
        switch (type) {
            case BOW -> weight = BaseWeaponStats.BOW.weight;
            case SWORD -> weight = BaseWeaponStats.SWORD.weight;
            case STAFF -> weight = BaseWeaponStats.STAFF.weight;
            case HELMET -> weight = BaseArmourStats.HELMET.weight;
            case CHEST -> weight = BaseArmourStats.CHEST.weight;
            case BOOTS -> weight = BaseArmourStats.BOOTS.weight;
            case MANA_POTION -> weight = BaseConsumablesStats.MANA_POTION.weight;
            case HEALTH_POTION -> weight = BaseConsumablesStats.HEALTH_POTION.weight;
        }
        if (material == Material.PLATE) {
            weight *= 2;
        }
        return weight;
    }

    private Rarity getRandomRarity(double chance) {
        if (chance > 29.50) return Rarity.COMMON;
        if (chance > 7.50) return Rarity.RARE;
        if (chance > 2.50) return Rarity.EPIC;
        if (chance > 0.50) return Rarity.MYTHIC;
        else return Rarity.LEGENDARY;
    }

    private Element getRandomElement(ItemType type, double chance) {
        if (type == ItemType.CONSUMABLE) return Element.NONE;
        if (chance > 80) return Element.WIND;
        if (chance > 60) return Element.EARTH;
        if (chance > 40) return Element.FIRE;
        if (chance > 20) return Element.WATER;
        else return Element.LIGHTNING;

    }

    private Type getRandmType(ItemType itemType, double chance) {

        Type type = null;
        switch (itemType) {
            case ARMOR -> type = getArmorType(chance);
            case CONSUMABLE -> type = getConsumableType(chance);
            case WEAPON -> type = getWeaponType(chance);
        }
        return type;
    }

    private Type getArmorType(double chance) {
        if (chance > 66.66) {
            return Type.HELMET;
        } else if (chance > 33.33) {
            return Type.CHEST;
        } else {
            return Type.BOOTS;
        }
    }

    private Type getConsumableType(double chance) {
        if (chance > 50) return Type.HEALTH_POTION;
        else return Type.MANA_POTION;
    }

    private Type getWeaponType(double chance) {
        if (chance > 66.66) {
            return Type.SWORD;
        } else if (chance > 33.33) {
            return Type.STAFF;
        } else {
            return Type.BOW;
        }
    }
}
