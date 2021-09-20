package com.yeumkyuseok.pracgrader;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Data implements Serializable {
    private static final String TAG = "Data";

    public List<User> users = new ArrayList<>();
    public List<User> tempUsers = new ArrayList<>();
    public List<Practical> practicals = new ArrayList<>();
    public List<TakenPrac> takenPracs = new ArrayList<>();

    DBModel dbModel = new DBModel();
    Cursor cursor;


    public Data() {}

    public void load(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        dbModel.db = dbHelper.getReadableDatabase();
        DBCursor dbCursor;
        // load values from users table
        cursor = dbModel.db.rawQuery("SELECT * FROM users", null);
        while (cursor.moveToNext()) {
            dbCursor = new DBCursor(cursor);
            users.add(dbCursor.getUser());
        }

        // load from practicals table
        cursor = dbModel.db.rawQuery("SELECT * FROM practicals", null);
        while (cursor.moveToNext()) {
            dbCursor = new DBCursor(cursor);
            practicals.add(dbCursor.getPractical());
        }

        // load from takenPrac table
        cursor = dbModel.db.rawQuery("SELECT * FROM takenPrac", null);
        while (cursor.moveToNext()) {
            dbCursor = new DBCursor(cursor);
            takenPracs.add(dbCursor.getTakenPrac());
        }

        getStudentList(1);

    }

    public boolean hasUser(String username){
        boolean hasUser = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUser_name().equals(username)) {
                hasUser = true;
            }
        }
        return hasUser;
    }

    public User getStudent(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUser_name().equals(username)) {
                return users.get(i);
            }
        }

        return null;
    }

    public void getStudentList(int role) {
        if (role == 0) {
            cursor = dbModel.db.rawQuery("SELECT * FROM users WHERE role > 0 ORDER BY name ASC", null);
        } else {
            cursor = dbModel.db.rawQuery("SELECT * FROM users WHERE role > 0 AND added_by = 1 ORDER BY name ASC", null);
        }
        DBCursor dbCursor;

        tempUsers.clear();

        while (cursor.moveToNext()) {
            dbCursor = new DBCursor(cursor);
            tempUsers.add(dbCursor.getUser());
        }

        for (int i = 0; i < tempUsers.size(); i++) {
            calculateMarksScored(tempUsers.get(i));
            calculateTotalMarksAvailable(tempUsers.get(i));
        }
    }

    public List<TakenPrac> getTakenPractical(String username) {

        List<TakenPrac> takenPracticals = new ArrayList<>();

        for (int i = 0; i < takenPracs.size(); i++) {
            if (takenPracs.get(i).username.equals(username)) {
                takenPracticals.add(takenPracs.get(i));
            }
        }

        return takenPracticals;
    }


    public void add(User user) {
        users.add(user);
        dbModel.addUser(user);
    }

    public void addTakenPrac(TakenPrac takenPrac) {
        takenPracs.add(takenPrac);
        dbModel.addTakenPrac(takenPrac);
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

    public boolean hasPractical(String pracTitle) {
        boolean pracExists = false;
        for (int i = 0; i < practicals.size(); i++) {
            if (pracTitle.equals(practicals.get(i).getTitle())) {
                pracExists = true;
            }
        }
        return pracExists;
    }

    public boolean hasStudent(String username) {
        boolean hasStudent = false;
        for (int i = 0; i < tempUsers.size(); i++) {
            if (username.equals(tempUsers.get(i).getUser_name())) {
                hasStudent = true;
            }
        }
        return hasStudent;
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

    public void generateData(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        dbModel.db = dbHelper.getWritableDatabase();

        dbModel.db.execSQL("INSERT INTO users (name, email, user_name, password, country, role, added_by) VALUES ('kevin', 'kevin@gmail.com', 'kev', '1234', 'malaysia', 1, 0), ('kim', 'kim@gmail.com', 'kim', '1234', 'hongkong', 1, 0), ('yura', 'yura@gmail.com', 'yura', '1234', 'korea', 2, 1), ('sally', 'sally@gmail.com', 'sally', '1234', 'korea', 2, 1), ('sarah', 'sarah@gmail.com', 'sarah', '1234', 'korea', 2, 1), ('sam', 'sam@gmail.com', 'sam', '1234', 'Philippines', 2, 1), ('john', 'john@gmail.com', 'john', '1234', 'Philippines', 2, 1), ('wailoon', 'wailoon@gmail.com', 'wailoon', '1234', 'China', 2, 1), ('Munkong', 'munkong@gmail.com', 'munkong', '1234', 'Thailand', 2, 1) ");
        dbModel.db.execSQL("INSERT INTO practicals (title, description, total_mark) VALUES ('DB test 1', 'test 1 for DATABASE SYSTEMS ', 50), ('DB test 2', 'test 2 for DATABASE SYSTEMS ', 30), ('DB assignment', 'Assignment for DATABASE SYSTEMS ', 20), ('MAD assignment 1', 'Assignment 1 for MAD ', 50), ('MAD assignment 2', 'Assignment 2 for MAD ', 50)");
        dbModel.db.execSQL("INSERT INTO takenPrac (user_name, prac_title, mark_scored) VALUES ('yura', 'DB test 1', 10), ('sally', 'DB test 1', 20), ('sarah', 'DB test 1', 30), ('sam', 'DB test 1', 32), ('john', 'DB test 1', 21), ('yura', 'DB test 2', 21), ('sally', 'DB test 2', 12), ('sam', 'DB test 2', 27), ('john', 'DB test 2', 7), ('yura', 'DB assignment', 20), ('sally', 'DB assignment', 2), ('sarah', 'DB assignment', 12), ('sam', 'DB assignment', 19), ('yura', 'MAD assignment 1', 50), ('yura', 'MAD assignment 2', 49)");

        DBCursor dbCursor;
        cursor = dbModel.db.rawQuery("SELECT * FROM users", null);

        while (cursor.moveToNext()) {
            dbCursor = new DBCursor(cursor);
            users.add(dbCursor.getUser());
        }

        cursor = dbModel.db.rawQuery("SELECT * FROM practicals", null);

        while (cursor.moveToNext()) {
            dbCursor = new DBCursor(cursor);
            practicals.add(dbCursor.getPractical());
        }
    }

    public double getPracMarkAvailable(String pracTitle) {
        double mark = 0;
        for (int i = 0; i < practicals.size(); i++) {
            if (practicals.get(i).getTitle().equals(pracTitle)) {
                mark = practicals.get(i).getMark();
            }
        }
        return mark;
    }

    public int getNumberOfStudents() {
        int count = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRole() == 2) {
                count++;
            }
        }
        return count;
    }

    public void calculateTotalMarksAvailable(User user) {
        String username = user.getUser_name();
        double totalMark = 0;
        List<String> pracTitleList = new ArrayList<>();
        List<Practical> practicalsTaken = new ArrayList<>();

        for (int i = 0; i < takenPracs.size(); i++) {
            if (takenPracs.get(i).getUsername().equals(username)) {
                String pracTitle = takenPracs.get(i).getPracTitle();
                pracTitleList.add(pracTitle);
            }
        }

        for (int i = 0; i < pracTitleList.size(); i++) {
            for (int j = 0; j < practicals.size(); j++) {
                if (pracTitleList.get(i).equals(practicals.get(j).getTitle())) {
                    totalMark += practicals.get(j).getMark();
                }
            }
        }
        user.setTotalMarkAvailable(totalMark);
    }

    public void calculateMarksScored(User user) {
        String username = user.getUser_name();
        double markScored = 0;
        for (int i = 0; i < takenPracs.size(); i++) {
            if (username.equals(takenPracs.get(i).getUsername())) {
                markScored += takenPracs.get(i).getMarkScored();
            }
        }
        Log.d(TAG, "calculateMarksScored: username: " + username + " markScored : " + markScored);
        user.setTotalMarkScored(markScored);
    }

    public void setStudentMark(List<User> list) {
        for (int i = 0; i < list.size(); i++) {
            calculateTotalMarksAvailable(list.get(i));
            calculateMarksScored(list.get(i));
        }
    }



}