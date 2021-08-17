package com.yeumkyuseok.simplegame;

public class Equipment extends Item {

    private double mass;


    public Equipment(String description, int value, double mass) {
        super(description, value);
        this.mass = mass;
    }

    public double getMass() {
        return mass;
    }

}
