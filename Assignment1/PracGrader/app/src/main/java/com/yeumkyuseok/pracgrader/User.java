package com.yeumkyuseok.pracgrader;

public class User {
    public static final int ADMIN = 0;
    public static final int INSTRUCTOR = 1;
    public static final int STUDENT = 2;

    private String name;
    private String email;
    private String user_name;
    private String password;
    private String country;
    private int role;
    private int added_by;
    private double totalMarkScored , totalMarkAvailable = 0;


    public User(String name, String email, String user_name, String password, String country, int role, int added_by) {
        if (password.length() != 4) {
            throw new IllegalArgumentException("wrong password formant");
        }
        this.name = name;
        this.email = email;
        this.user_name = user_name;
        this.password = password;
        this.country = country;
        this.role = role;
        this.added_by = added_by;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getAdded_by() {
        return added_by;
    }

    public void setAdded_by(int added_by) {
        this.added_by = added_by;
    }

    public double getTotalMarkScored() {
        return totalMarkScored;
    }

    public void setTotalMarkScored(double totalMarkScored) {
        this.totalMarkScored = totalMarkScored;
    }

    public double getTotalMarkAvailable() {
        return totalMarkAvailable;
    }

    public void setTotalMarkAvailable(double totalMarkAvailable) {
        this.totalMarkAvailable = totalMarkAvailable;
    }

    public double getPercentage() {
        return (totalMarkScored / totalMarkAvailable) * 100;
    }
}