package com.yeumkyuseok.pracgrader;

public class Practical {

    private String title;
    private String description;
    private double totalMark;

    public Practical(String title, String description, double totalMark) {
        this.title = title;
        this.description = description;
        this.totalMark = totalMark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMark() {
        return totalMark;
    }

    public void setMark(double mark) {
        this.totalMark = mark;
    }

}
