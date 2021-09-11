package com.yeumkyuseok.alliesandenemies;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class DBModel {

    SQLiteDatabase db;

    public void removeFaction(Faction faction) {
        String[] whereValue= {String.valueOf(faction.getId())};
        db.delete(DBSchema.DBTable.NAME, DBSchema.DBTable.Cols.NAME + " =?", whereValue );
    }

    public void updateFAction(Faction faction) {
        ContentValues cv = new ContentValues();
        cv.put(DBSchema.DBTable.Cols.ID, faction.getId());
        cv.put(DBSchema.DBTable.Cols.NAME, faction.getId());
        cv.put(DBSchema.DBTable.Cols.STR, faction.getId());
        cv.put(DBSchema.DBTable.Cols.REL, faction.getId());

        String[] whereValue =  {String.valueOf(faction)};

        db.update(DBSchema.DBTable.NAME, cv, DBSchema.DBTable.Cols.ID + " = ?", whereValue);
    }
}
