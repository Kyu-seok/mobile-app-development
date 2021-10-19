package com.yeumkyuseok.mathtest;

import java.util.List;

public class EmailAddr {
    String fullName, emails[];

    public EmailAddr(String fullName, String emails[]) {
        this.fullName = fullName;
        this.emails = emails;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String[] getEmails() {
        return emails;
    }

    public void setEmails(String[] emails) {
        this.emails = emails;
    }
}
