package com.example.WarriorsTest.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "equipped_items")
public class EquippedItemsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private ItemEntity weapon;
    @OneToOne
    private ItemEntity head;
    @OneToOne
    private ItemEntity chest;
    @OneToOne
    private ItemEntity boots;


    public EquippedItemsEntity() {
    }

    public ItemEntity getWeapon() {
        return weapon;
    }

    public EquippedItemsEntity setWeapon(ItemEntity weapon) {
        this.weapon = weapon;
        return this;
    }

    public ItemEntity getHead() {
        return head;
    }

    public EquippedItemsEntity setHead(ItemEntity head) {
        this.head = head;
        return this;
    }

    public ItemEntity getChest() {
        return chest;
    }

    public EquippedItemsEntity setChest(ItemEntity chest) {
        this.chest = chest;
        return this;
    }

    public ItemEntity getBoots() {
        return boots;
    }

    public EquippedItemsEntity setBoots(ItemEntity boots) {
        this.boots = boots;
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
