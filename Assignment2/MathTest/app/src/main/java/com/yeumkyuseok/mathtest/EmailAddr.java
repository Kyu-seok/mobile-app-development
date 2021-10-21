package com.yeumkyuseok.mathtest;

import java.util.ArrayList;
import java.util.List;

public class EmailAddr {
    String fullName;
    List<String> emails = new ArrayList<>();

    public EmailAddr(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public void addEmail(String email) {
        emails.add(email);
    }
}
