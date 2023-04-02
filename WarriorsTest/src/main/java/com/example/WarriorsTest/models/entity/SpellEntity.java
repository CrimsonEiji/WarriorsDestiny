package com.example.WarriorsTest.models.entity;

import com.example.WarriorsTest.enums.HeroClass;
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
    private int stat;

    @Enumerated(EnumType.STRING)
    private HeroClass forClass;

    @Column
    private int manaConsumption;

    public int getManaConsumption() {
        return manaConsumption;
    }

    public SpellEntity setManaConsumption(int manaConsumption) {
        this.manaConsumption = manaConsumption;
        return this;
    }

    public Long getId() {
        return id;
    }

    public SpellEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public HeroClass getForClass() {
        return forClass;
    }

    public SpellEntity setForClass(HeroClass forClass) {
        this.forClass = forClass;
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

    public int getStat() {
        return stat;
    }

    public SpellEntity setStat(int stat) {
        this.stat = stat;
        return this;
    }

    public SpellEntity() {
    }
}
