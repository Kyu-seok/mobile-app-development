package com.yeumkyuseok.alliesandenemies;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.yeumkyuseok.alliesandenemies.DBSchema.DBTable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "factions.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DBTable.NAME + "(" +
                DBTable.Cols.ID + " INTEGER, " +
                DBTable.Cols.NAME + " TEXT, " +
                DBTable.Cols.STR + "INTEGER, " +
                DBTable.Cols.REL + "INTEGER" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new UnsupportedOperationException("sorry");
    }
}
