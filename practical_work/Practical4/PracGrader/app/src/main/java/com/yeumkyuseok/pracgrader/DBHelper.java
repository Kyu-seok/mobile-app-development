package com.yeumkyuseok.pracgrader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.yeumkyuseok.pracgrader.DBSchema.*;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "prac_grader.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + UserTable.NAME + "(" +
                UserTable.Cols.NAME + " TEXT, " +
                UserTable.Cols.EMAIL + " TEXT, " +
                UserTable.Cols.USERNAME + " TEXT PRIMARY KEY, " +
                UserTable.Cols.PASSWORD + "  TEXT, " +
                UserTable.Cols.COUNTRY + " TEXT," +
                UserTable.Cols.ROLE + " INTEGER" +
                ")");

        db.execSQL("create table " + PracticalTable.NAME + "(" +
                PracticalTable.Cols.TITLE + " TEXT PRIMARY KEY, " +
                PracticalTable.Cols.DESC + " TEXT, " +
                PracticalTable.Cols.MARK + " REAL, " +
                PracticalTable.Cols.STUDENT_NAME + " TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new UnsupportedOperationException("sorry");
    }
}
