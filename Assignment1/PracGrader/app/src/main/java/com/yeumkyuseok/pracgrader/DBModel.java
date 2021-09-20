package com.yeumkyuseok.pracgrader;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.yeumkyuseok.pracgrader.DBSchema.*;

public class DBModel {
    SQLiteDatabase db;

    public void addUser(User user){
        ContentValues cv = new ContentValues();
        cv.put(UserTable.Cols.NAME, user.getName());
        cv.put(UserTable.Cols.EMAIL, user.getEmail());
        cv.put(UserTable.Cols.USERNAME, user.getUser_name());
        cv.put(UserTable.Cols.PASSWORD, user.getPassword());
        cv.put(UserTable.Cols.COUNTRY, user.getCountry());
        cv.put(UserTable.Cols.ROLE, user.getRole());
        cv.put(UserTable.Cols.ADDEDBY, user.getAdded_by());

        db.insert(UserTable.NAME, null, cv);
    }

    public void editUser(User user){

    }

    public void deleteUser(User user){

    }

    public void addPractical(Practical practical) {

    }

    public void editPractical(Practical practical) {

    }


    public void deletePractical(Practical practical) {

    }

    public void addTakenPrac(TakenPrac takenPrac) {
        ContentValues cv = new ContentValues();
        cv.put(TakenPracTable.Cols.USERNAME, takenPrac.getUsername());
        cv.put(TakenPracTable.Cols.PRAC_TITLE, takenPrac.getPracTitle());
        cv.put(TakenPracTable.Cols.MARK_SCORED, takenPrac.getMarkScored());
        db.insert(TakenPracTable.NAME, null, cv);
    }

}
