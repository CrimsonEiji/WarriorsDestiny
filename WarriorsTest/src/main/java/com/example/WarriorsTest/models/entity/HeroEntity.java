package com.example.WarriorsTest.models.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "heros")
public class HeroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany
    private List<ItemEntity> inventory;

    @OneToMany
    private List<SpellEntity> spells;




    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
