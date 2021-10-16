package com.yeumkyuseok.mathtest;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.yeumkyuseok.pracgrader.DBSchema.*;

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

    public User getUser() {
        String name = getString(getColumnIndex(UserTable.Cols.NAME));
        String email = getString(getColumnIndex(UserTable.Cols.EMAIL));
        String user_name = getString(getColumnIndex(UserTable.Cols.USERNAME));
        String password = getString(getColumnIndex(UserTable.Cols.PASSWORD));
        String country = getString(getColumnIndex(UserTable.Cols.COUNTRY));
        int role = getInt(getColumnIndex(UserTable.Cols.ROLE));
        int added_by = getInt(getColumnIndex(UserTable.Cols.ADDEDBY));

        return new User(name, email, user_name, password, country, role, added_by);
    }

    public Practical getPractical() {
        String title = getString(getColumnIndex(PracticalTable.Cols.TITLE));
        String description = getString(getColumnIndex(PracticalTable.Cols.DESC));
        double totalMark = getDouble(getColumnIndex(PracticalTable.Cols.MARK));

        return new Practical(title, description, totalMark);
    }

    public TakenPrac getTakenPrac() {
        String username = getString(getColumnIndex(TakenPracTable.Cols.USERNAME));
        String pracTitle = getString(getColumnIndex(TakenPracTable.Cols.PRAC_TITLE));
        double markScored = getDouble(getColumnIndex(TakenPracTable.Cols.MARK_SCORED));

        return new TakenPrac(username, pracTitle, markScored);
    }
}
