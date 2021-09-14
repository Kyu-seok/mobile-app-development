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
        boolean isUnique = false;
        if (users.size() == 0) {
            isUnique = true;
        } else {
            for (int i = 0 ; i < users.size(); i++) {
                if (users.get(i).getRole() == 0) {
                    isUnique = true;
                }
            }
        }
        return isUnique;
    }

    public boolean canLogin(String inputUsername, String inputPassword) {
        boolean loginSuccess = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUser_name().equals(inputUsername) && users.get(i).getPassword().equals(inputPassword)) {
                loginSuccess = true;
            }
        }
        return loginSuccess;
    }

    public int checkRole(String username) {
        int role = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUser_name().equals(username)) {
                role = users.get(i).getRole();
            }
        }
        return role;
    }

    // todo: implement edit
    // todo: implement delete



}