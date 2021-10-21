package com.yeumkyuseok.mathtest;

import java.util.ArrayList;
import java.util.List;

public class Phone {
    String fullName;
    List<String> phoneNumbers = new ArrayList<>();

    public Phone(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void addPhoneNo(String phoneNo) {
        phoneNumbers.add(phoneNo);
    }
}
