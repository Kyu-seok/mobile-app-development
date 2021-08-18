package com.yeumkyuseok.simplegame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Area implements Serializable {

    private boolean town;
    private List<Item> items = new ArrayList<>();

    public Area(boolean isTown) {
        this.town = isTown;
    }

    public void addItems(Item item) {
        items.add(item);
    }

    public boolean isTown() {
        return town;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean checkIsEmpty() {
        return (items.isEmpty() ? true : false);
    }

}
