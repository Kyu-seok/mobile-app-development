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
        String phoneNum = getString(getColumnIndex("phone_num"));
        String email = getString(getColumnIndex("email"));
        String photo = getString(getColumnIndex("photo"));

        return new Student(firstName, lastName, phoneNum, email, photo);
    }

    public Result getResult() {
        String firstName = getString(getColumnIndex("first_name"));
        String lastName = getString(getColumnIndex("last_name"));
        int score = getInt(getColumnIndex("score"));
        String startTime = getString(getColumnIndex("start_time"));
        int time_taken = getInt(getColumnIndex("time_taken"));

        return new Result(firstName, lastName, score, startTime, time_taken);
    }

}
