package com.example.WarriorsTest.models.DTO;

import com.example.WarriorsTest.enums.*;

public class ShopItemDTO {

    private Long id;

    private String name;

    private Integer stat;

    private int durability;

    private Rarity rarity;

    private String description;

    private ItemType itemType;

    private Type type;

    private Double weight;

    private Material material;

    private Element element;

    private int price;

    public int getPrice() {
        return price;
    }

    public ShopItemDTO setPrice(int price) {
        this.price = price;
        return this;
    }

    public ShopItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public ShopItemDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShopItemDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getStat() {
        return stat;
    }

    public ShopItemDTO setStat(Integer stat) {
        this.stat = stat;
        return this;
    }

    public int getDurability() {
        return durability;
    }

    public ShopItemDTO setDurability(int durability) {
        this.durability = durability;
        return this;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public ShopItemDTO setRarity(Rarity rarity) {
        this.rarity = rarity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ShopItemDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public ShopItemDTO setItemType(ItemType itemType) {
        this.itemType = itemType;
        return this;
    }

    public Type getType() {
        return type;
    }

    public ShopItemDTO setType(Type type) {
        this.type = type;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public ShopItemDTO setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public ShopItemDTO setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public Element getElement() {
        return element;
    }

    public ShopItemDTO setElement(Element element) {
        this.element = element;
        return this;
    }
}
