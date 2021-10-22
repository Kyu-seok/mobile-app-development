package com.yeumkyuseok.mathtest;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBModel {
    SQLiteDatabase db;
    private static final String TAG = "DBModel";

    public void addStudent(Student student) {
        ContentValues cv = new ContentValues();
        cv.put("first_name", student.getFirstName());
        cv.put("last_name", student.getLastName());
        cv.put("full_name", student.getFullName());
        cv.put("photo", student.getPhoto());

        db.insert("student", null, cv);
    }

    public void addEmail(EmailAddr email) {
        ContentValues cv = new ContentValues();
        for (int i = 0; i < email.getEmails().size(); i++) {
            cv.put("full_name", email.getFullName());
            cv.put("email_adr", email.getEmails().get(i));

            db.insert("email", null, cv);
        }
    }

    public void addPhone(Phone phone) {
        ContentValues cv = new ContentValues();
        for (int i = 0; i < phone.getPhoneNumbers().size(); i++) {
            cv.put("full_name", phone.getFullName());
            cv.put("phone_no", phone.getPhoneNumbers().get(i));

            db.insert("phone", null, cv);
        }
    }

    public void addMark(Student student, int mark) {
        ContentValues cv = new ContentValues();

        cv.put("first_name", student.getFirstName());
        cv.put("last_name", student.getLastName());
        cv.put("full_name", student.getFullName());
        cv.put("photo", student.getPhoto());
        cv.put("mark", mark);

        String[] whereValue =  {student.getFullName()};

        db.update("student", cv, "full_name = ?" , whereValue);
    }

    public void editStudent(Student student) {
        Log.d(TAG, "editStudent: add function here");
    }

    public void deleteStudent(Student student) {
        String[] whereValue = {String.valueOf(student.getFullName())};
        db.delete("student", "full_name = ?", whereValue);
    }

    public void addResult(String fullName, int score, String startTime, String timeTaken) {
        ContentValues cv = new ContentValues();
        cv.put("full_NAME", fullName);
        cv.put("score", score);
        cv.put("start_time", startTime);
        cv.put("time_taken", timeTaken);

        db.insert("result", null, cv);
    }

    public void editResult(Student student, int score, String startTime, int timeTaken) {
        Log.d(TAG, "editResult: add function here");
    }

    public void deleteResult(Student student) {
        Log.d(TAG, "deleteResult: add function here");
    }

    public void addPhotoToStudent(Student student, String photoPath) {
        ContentValues cv = new ContentValues();

        cv.put("first_name", student.getFirstName());
        cv.put("last_name", student.getLastName());
        cv.put("full_name", student.getFullName());
        cv.put("photo", photoPath);

        String[] whereValue =  {student.fullName};

        db.update("student", cv,"full_name = ?" , whereValue);
    }
    /*
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

    public void editUser(String username, User user) {
        ContentValues cv = new ContentValues();

        cv.put(UserTable.Cols.NAME, user.getName());
        cv.put(UserTable.Cols.EMAIL, user.getEmail());
        cv.put(UserTable.Cols.USERNAME, username);
        cv.put(UserTable.Cols.PASSWORD, user.getPassword());
        cv.put(UserTable.Cols.COUNTRY, user.getCountry());
        cv.put(UserTable.Cols.ROLE, user.getRole());
        cv.put(UserTable.Cols.ADDEDBY, user.getAdded_by());

        String[] whereValue =  {username};

        db.update(UserTable.NAME, cv, UserTable.Cols.USERNAME + " = ?" , whereValue);
    }

    public void deleteUser(User user){
        String[] whereValue= {String.valueOf(user.getUser_name())};
        db.delete(UserTable.NAME, UserTable.Cols.USERNAME + " = ?", whereValue );
    }

    public void addPractical(Practical practical) {
        ContentValues cv = new ContentValues();
        cv.put(PracticalTable.Cols.TITLE, practical.getTitle());
        cv.put(PracticalTable.Cols.DESC, practical.getDescription());
        cv.put(PracticalTable.Cols.MARK, practical.getMark());
        db.insert(PracticalTable.NAME, null, cv);
    }

    public void editPractical(String title, Practical practical) {
        ContentValues cv = new ContentValues();


        cv.put(PracticalTable.Cols.TITLE, practical.getTitle());
        cv.put(PracticalTable.Cols.DESC, practical.getDescription());
        cv.put(PracticalTable.Cols.MARK, practical.getMark());

        String[] whereValue =  {title};
        db.update(PracticalTable.NAME, cv, PracticalTable.Cols.TITLE + " = ?" , whereValue);
    }


    public void deletePractical(Practical practical) {
        String[] whereValue= {String.valueOf(practical.getTitle())};
        db.delete(PracticalTable.NAME, PracticalTable.Cols.TITLE + " = ?", whereValue );
    }

    public void addTakenPrac(TakenPrac takenPrac) {
        ContentValues cv = new ContentValues();
        cv.put(TakenPracTable.Cols.USERNAME, takenPrac.getUsername());
        cv.put(TakenPracTable.Cols.PRAC_TITLE, takenPrac.getPracTitle());
        cv.put(TakenPracTable.Cols.MARK_SCORED, takenPrac.getMarkScored());
        db.insert(TakenPracTable.NAME, null, cv);
    }

    public void editTakenPrac(String username, String pracTitle, double markScored) {
        ContentValues cv = new ContentValues();

        cv.put(TakenPracTable.Cols.USERNAME, username);
        cv.put(TakenPracTable.Cols.PRAC_TITLE, pracTitle);
        cv.put(TakenPracTable.Cols.MARK_SCORED, markScored);

        String[] whereValue =  {username, pracTitle};

        db.update(TakenPracTable.NAME, cv, TakenPracTable.Cols.USERNAME + " = ? AND " + TakenPracTable.Cols.PRAC_TITLE + " = ?" , whereValue);
    }

    public void deleteTakenPrac(String username, String pracTitle) {
        String[] whereValue= {username, pracTitle};
        db.delete(TakenPracTable.NAME, TakenPracTable.Cols.USERNAME + " = ? AND " + TakenPracTable.Cols.PRAC_TITLE + " = ?", whereValue );
    }
     */

}
