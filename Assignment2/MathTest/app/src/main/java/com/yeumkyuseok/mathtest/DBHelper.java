package com.yeumkyuseok.mathtest;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yeumkyuseok.assignment2.DBSchema.*;

import java.io.Serializable;

public class DBHelper extends SQLiteOpenHelper implements Serializable {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "math_test.db";

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
                UserTable.Cols.COUNTRY + " TEXT, " +
                UserTable.Cols.ROLE + " INTEGER, " +
                UserTable.Cols.ADDEDBY + " INTEGER" +
                ")");

        db.execSQL("create table " + PracticalTable.NAME + "(" +
                PracticalTable.Cols.TITLE + " TEXT PRIMARY KEY, " +
                PracticalTable.Cols.DESC + " TEXT, " +
                PracticalTable.Cols.MARK + " REAL " +
                ")");

        db.execSQL("create table " + TakenPracTable.NAME + "(" +
                TakenPracTable.Cols.USERNAME + " TEXT, " +
                TakenPracTable.Cols.PRAC_TITLE + " TEXT, " +
                TakenPracTable.Cols.MARK_SCORED + " REAL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new UnsupportedOperationException("sorry");
    }
}