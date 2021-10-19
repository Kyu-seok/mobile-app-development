package com.yeumkyuseok.mathtest;

import java.io.Serializable;

public class Student implements Serializable {

    String firstName, lastName, phoneNum[], email[], photo, fullName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        // this.phoneNum = phoneNum;
        // this.email = email;
        this.photo = photo;
        this.fullName = firstName + " " + lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /*
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
     */

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFullName() {
        return fullName;
    }
}
