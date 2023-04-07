package com.example.WarriorsTest.models.DTO;

import com.example.WarriorsTest.enums.*;

public class UserItemDTO {

    private Long id;

    private String name;

    private Integer stat;

    private int durability;

    private int price;

    private Rarity rarity;

    private String description;

    private ItemType itemType;

    private Type type;

    private Double weight;

    private Material material;

    private Element element;

    public UserItemDTO() {
    }

    public int getPrice() {
        return price;
    }

    public UserItemDTO setPrice(int price) {
        this.price = price;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserItemDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserItemDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getStat() {
        return stat;
    }

    public UserItemDTO setStat(Integer stat) {
        this.stat = stat;
        return this;
    }

    public int getDurability() {
        return durability;
    }

    public UserItemDTO setDurability(int durability) {
        this.durability = durability;
        return this;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public UserItemDTO setRarity(Rarity rarity) {
        this.rarity = rarity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserItemDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public UserItemDTO setItemType(ItemType itemType) {
        this.itemType = itemType;
        return this;
    }

    public Type getType() {
        return type;
    }

    public UserItemDTO setType(Type type) {
        this.type = type;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public UserItemDTO setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public UserItemDTO setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public Element getElement() {
        return element;
    }

    public UserItemDTO setElement(Element element) {
        this.element = element;
        return this;
    }
}
