package com.yeumkyuseok.simplegame;

import java.io.Serializable;

public class GameMap implements Serializable {

    Area[][] grid  = new Area[3][3];

    public GameMap() {

        grid[0][0] = new Area(true);
        grid[0][1] = new Area(false);
        grid[0][2] = new Area(true);
        grid[1][0] = new Area(false);
        grid[1][1] = new Area(true);
        grid[1][2] = new Area(false);
        grid[2][0] = new Area(true);
        grid[2][1] = new Area(false);
        grid[2][2] = new Area(true);


        //  list of items
        Equipment jadeMonkey = new Equipment("Jade Monkey", 10, 10);
        Equipment roadMap = new Equipment("Road Map", 15, 2);
        Equipment iceScraper = new Equipment("Ice Scraper", 20, 8);

        Equipment item1 = new Equipment("Helmet", 2, 4.0);
        Equipment item2 = new Equipment("Shoe", 3, 0.5);
        Equipment item3 = new Equipment("Shirt", 4, 1.5);
        Equipment item4 = new Equipment("Pants", 3, 3.2);
        Equipment item5 = new Equipment("Teddy Bear", 5, 7.7);
        Equipment item6 = new Equipment("iphone 12 max", 32, 5.8);
        Equipment item7 = new Equipment("m1 macbook air", 7, 20.0);
        Equipment item8 = new Equipment("calculator", 1, 3.2);
        Equipment item9 = new Equipment("airpods pro", 22, 0.1);

        Food food1 = new Food("KFC Bucket Combo Meal", 12, 32.0);
        Food food2 = new Food("apple(poisoned)", 3, -50.0);
        Food food3 = new Food("Burger King Whopper Combo", 12, 32.0);
        Food food4 = new Food("banana", 2, 8.0);
        Food food5 = new Food("Big Mac Combo Meal", 12, 32.0);
        Food food6 = new Food("Rotten meat", 8, -15.0);
        Food food7 = new Food("Iced Americano", 10, 0.5);
        Food food8 = new Food("Expired canned tuna", 3, -5.0);
        Food food9 = new Food("apple(poisoned)", 2, -10.0);


        //  adding Items to Area
        grid[0][0].addItems(jadeMonkey);
        grid[1][1].addItems(roadMap);
        grid[0][2].addItems(iceScraper);

        grid[0][0].addItems(item1);
        grid[0][1].addItems(item2);
        grid[0][2].addItems(item3);
        grid[1][0].addItems(item4);
        grid[1][1].addItems(item5);
        grid[1][2].addItems(item6);
        grid[2][0].addItems(item7);
        grid[2][1].addItems(item8);
        grid[2][2].addItems(item9);

        grid[0][0].addItems(food1);
        grid[0][1].addItems(food2);
        grid[0][2].addItems(food3);
        grid[1][0].addItems(food4);
        grid[1][1].addItems(food5);
        grid[1][2].addItems(food6);
        grid[2][0].addItems(food7);
        grid[2][1].addItems(food8);
        grid[2][2].addItems(food9);

    }


    public void setArea(int col, int row, Area area) {
        this.grid[col][row] = area;
    }

    public Area getArea(int col, int row) {
        return this.grid[col][row];
    }



}
