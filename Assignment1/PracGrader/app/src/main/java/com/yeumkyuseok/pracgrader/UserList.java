package com.yeumkyuseok.pracgrader;

import android.content.Context;
import android.database.Cursor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserList implements Serializable {

    public List<User> users = new ArrayList<>();
    DBModel dbModel = new DBModel();
    Cursor cursor;

    public UserList() {}

    public void load(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        dbModel.db = dbHelper.getReadableDatabase();
        cursor = dbModel.db.rawQuery("SELECT * FROM users", null);
        DBCursor dbCursor;

        while (cursor.moveToNext()) {
            dbCursor = new DBCursor(cursor);
            users.add(dbCursor.getUser());
        }

    }

    public void add(User user) {
        users.add(user);
        dbModel.addUser(user);
    }

    public boolean hasAdmin() {
        boolean adminExists = false;
        for(int i = 0; i < users.size(); i++ ) {
            if(users.get(i).getRole() == 0) {
                adminExists = true;
            }
        }

        return adminExists;
    }

    public boolean checkUnique(String userName) {

        return false;
    }

    // todo: implement edit
    // todo: implement delete



}