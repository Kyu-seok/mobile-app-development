package com.yeumkyuseok.pracgrader;

public class Practical {

    private String title;
    private String description;
    private double mark;
    private String student_name;

    public Practical(String title, String description, double mark, String student_name) {
        this.title = title;
        this.description = description;
        this.mark = mark;
        this.student_name = student_name;
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
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }
}
