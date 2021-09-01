package com.yeumkyuseok.simplegame;

public class Food extends Item{

    private double health;

    public Food(String description, int value, double health) {
        super(description, value);
        this.health = health;
    }

    public double getHealth() {
        return health;
    }

}
