package com.yeumkyuseok.pracgrader;

public class TakenPrac {
    String username, pracTitle;
    double markScored;

    public TakenPrac(String username, String pracTitle, double markScored) {
        this.username = username;
        this.pracTitle = pracTitle;
        this.markScored = markScored;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPracTitle() {
        return pracTitle;
    }

    public void setPracTitle(String pracTitle) {
        this.pracTitle = pracTitle;
    }

    public double getMarkScored() {
        return markScored;
    }

    public void setMarkScored(double markScored) {
        this.markScored = markScored;
    }
}
