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

    public User(String name, String email, String user_name, String password, String country, int role) {
        if (password.length() != 4) {
            throw new IllegalArgumentException("wrong password formant");
        }
        this.name = name;
        this.email = email;
        this.user_name = user_name;
        this.password = password;
        this.country = country;
        this.role = role;
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
}
