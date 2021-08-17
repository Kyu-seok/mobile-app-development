package com.yeumkyuseok.simplegame;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int rowLocation;
    private int colLocation;
    private int cash;
    private double health;
    private double equipmentMass;
    private List<Equipment> equipment;

    public Player() {
        this.rowLocation = 0;
        this.colLocation = 0;
        this.cash = 50;
        this.health = 100;
        this.equipmentMass = 0;
        this.equipment = new ArrayList<>();
    }

    public int getRowLocation() {
        return rowLocation;
    }

    public int getColLocation() {
        return colLocation;
    }

    public int getCash() {
        return cash;
    }

    public double getHealth() {
        return health;
    }

    public double getEquipmentMass() {
        return equipmentMass;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void addEquipment(Equipment equipment) {
        this.equipment.add(equipment);
        this.equipmentMass += equipment.getMass();
    }

    public void setRowLocation(int rowLocation) {
        this.rowLocation = rowLocation;
    }

    public void setColLocation(int colLocation) {
        this.colLocation = colLocation;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setEquipmentMass(double equipmentMass) {
        this.equipmentMass = equipmentMass;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }


}
