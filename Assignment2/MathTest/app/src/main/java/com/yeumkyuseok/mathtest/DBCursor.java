package com.yeumkyuseok.mathtest;

import android.database.Cursor;
import android.database.CursorWrapper;

public class DBCursor extends CursorWrapper {

    public DBCursor(Cursor cursor) {
        super(cursor);
    }

    public Student getStudent() {
        String firstName = getString(getColumnIndex("first_name"));
        String lastName = getString(getColumnIndex("last_name"));
        String photo = getString(getColumnIndex("photo"));
        int mark = getInt(getColumnIndex("mark"));

        Student student = new Student(firstName, lastName, mark);
        student.setPhoto(photo);

        // return new Student(firstName, lastName);
        return student;
    }

    public Result getResult() {
        String fullName = getString(getColumnIndex("full_NAME"));
        int score = getInt(getColumnIndex("score"));
        String startTime = getString(getColumnIndex("start_time"));
        String time_taken = getString(getColumnIndex("time_taken"));

        return new Result(fullName, score, startTime, time_taken);
    }

    public String getResultMessage() {
        String fullName = getString(getColumnIndex("full_NAME"));
        int score = getInt(getColumnIndex("score"));
        String startTime = getString(getColumnIndex("start_time"));
        String time_taken = getString(getColumnIndex("time_taken"));

        String msg =
                "Text Taken by : " + fullName
                + "\nMark Scored : " + score
                + "\nStart Time : " + startTime
                + "\nTime Taken : " + time_taken;

        return msg;

    }

    public String getStringEmail() {
        String email = getString(getColumnIndex("email_adr"));
        return email;
    }

    public String getStringPhone() {
        String phone = getString(getColumnIndex("phone_no"));
        return phone;
    }

}
