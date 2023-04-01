package com.example.WarriorsTest.models.entity;

import com.example.WarriorsTest.enums.*;
import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer stat;

    @Column(nullable = false)
    private int durability;

    @Column(nullable = false)
    private Rarity rarity;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(nullable = false)
    private Double weight;

    @Enumerated
    private Material material;

    @Enumerated(EnumType.STRING)
    private Element element;

    public Material getMaterial() {
        return material;
    }

    public ItemEntity setMaterial(Material material) {
        this.material = material;
        return this;
    }


    public Element getElement() {
        return element;
    }

    public ItemEntity setElement(Element element) {
        this.element = element;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getStat() {
        return stat;
    }

    public ItemEntity setStat(Integer stat) {
        this.stat = stat;
        return this;
    }

    public int getDurability() {
        return durability;
    }

    public ItemEntity setDurability(int durability) {
        this.durability = durability;
        return this;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public ItemEntity setRarity(Rarity rarity) {
        this.rarity = rarity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public com.example.WarriorsTest.enums.ItemType getItemType() {
        return itemType;
    }

    public ItemEntity setItemType(com.example.WarriorsTest.enums.ItemType itemType) {
        this.itemType = itemType;
        return this;
    }

    public Type getType() {
        return type;
    }

    public ItemEntity setType(Type type) {
        this.type = type;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public ItemEntity setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public ItemEntity() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("""
                        itemType = %s
                        description = %s
                        durability = %s
                        element = %s
                        material = %s
                        name = %s
                        rarity = %s
                        stat = %s
                        type = %s
                        weight = %s
                        """,
                itemType,
                description,
                durability,
                element,
                material,
                name,
                rarity,
                stat,
                type,
                weight);
    }
}
