package com.example.WarriorsTest.models.entity;

import com.example.WarriorsTest.enums.SpellType;
import jakarta.persistence.*;

@Entity
@Table(name = "spells")
public class SpellEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private SpellType spellType;

    @Column
    private String stat;



    public Long getId() {
        return id;
    }

    public SpellEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SpellEntity setName(String name) {
        this.name = name;
        return this;
    }

    public SpellType getSpellType() {
        return spellType;
    }

    public SpellEntity setSpellType(SpellType spellType) {
        this.spellType = spellType;
        return this;
    }

    public String getStat() {
        return stat;
    }

    public SpellEntity setStat(String stat) {
        this.stat = stat;
        return this;
    }

    public SpellEntity() {
    }
}
