package com.yeumkyuseok.simplegame;

public abstract class Item{

    private String description;
    private int value;

    public Item(String description, int value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

}
