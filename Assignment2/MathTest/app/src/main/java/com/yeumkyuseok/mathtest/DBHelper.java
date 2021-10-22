package com.yeumkyuseok.mathtest;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yeumkyuseok.mathtest.DBSchema.*;

import java.io.Serializable;

public class DBHelper extends SQLiteOpenHelper implements Serializable {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "math_test.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE student (\n" +
                        "    first_name VARCHAR(30),\n" +
                        "    last_name VARCHAR(30),\n" +
                        "    full_name VARCHAR(60),\n" +
                        "    photo VARCHAR(300),\n" +
                        "    mark INT DEFAULT 0" +
                        ");"
        );

        db.execSQL(
                "CREATE TABLE result (\n" +
                        "    full_NAME VARCHAR(60),\n" +
                        "    score INTEGER,\n" +
                        "    start_time VARCHAR(30),\n" +
                        "    time_taken VARCHAR(30)\n" +
                        ");"
        );

        db.execSQL(
                "CREATE TABLE phone (\n" +
                        "    full_NAME VARCHAR(60),\n" +
                        "   phone_no VARCHAR (15)" +
                        ");"
        );

        db.execSQL(
                "CREATE TABLE email (\n" +
                        "    full_NAME VARCHAR(60),\n" +
                        "   email_adr VARCHAR(40)\n" +
                        ");"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new UnsupportedOperationException("sorry");
    }
}