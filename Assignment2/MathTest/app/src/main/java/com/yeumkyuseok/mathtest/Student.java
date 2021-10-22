package com.yeumkyuseok.mathtest;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {

    String firstName, lastName, photo, fullName;
    List<String> emails;
    List<String> phones;
    int mark;

    public Student(String firstName, String lastName, int mark) {
        this.firstName = firstName;
        this.lastName = lastName;
        // this.phoneNum = phoneNum;
        // this.email = email;
        this.photo = "";
        this.fullName = firstName + " " + lastName;
        this.mark = mark;
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

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFullName() {
        return fullName;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
