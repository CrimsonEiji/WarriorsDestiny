package com.example.WarriorsTest.models.DTO;

import com.example.WarriorsTest.enums.*;

public class ItemForInventoryDTO {
    private Long id;

    private String name;

    private Type type;

    public ItemForInventoryDTO() {
    }

    public Long getId() {
        return id;
    }

    public ItemForInventoryDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemForInventoryDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Type getType() {
        return type;
    }

    public ItemForInventoryDTO setType(Type type) {
        this.type = type;
        return this;
    }
}
