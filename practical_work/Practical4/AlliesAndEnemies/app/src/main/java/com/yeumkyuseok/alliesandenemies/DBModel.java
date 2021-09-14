package com.yeumkyuseok.alliesandenemies;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class DBModel {

    SQLiteDatabase db;

    public void removeFaction(Faction faction) {
        String[] whereValue= {String.valueOf(faction.getId())};
        db.delete(DBSchema.DBTable.NAME, DBSchema.DBTable.Cols.ID + " = ?", whereValue );
    }

    public void updateFaction(Faction faction) {
        ContentValues cv = new ContentValues();
        cv.put(DBSchema.DBTable.Cols.ID, faction.getId());
        cv.put(DBSchema.DBTable.Cols.NAME, faction.getName());
        cv.put(DBSchema.DBTable.Cols.STR, faction.getStrength());
        cv.put(DBSchema.DBTable.Cols.REL, faction.getRelationship());

        String[] whereValue =  {String.valueOf(faction.getId())};

        db.update(DBSchema.DBTable.NAME, cv, DBSchema.DBTable.Cols.ID + " = ?", whereValue);
    }

    public void addFaction(Faction faction) {
        ContentValues cv = new ContentValues();
        cv.put(DBSchema.DBTable.Cols.ID, faction.getId());
        cv.put(DBSchema.DBTable.Cols.NAME, faction.getName());
        cv.put(DBSchema.DBTable.Cols.STR, faction.getStrength());
        cv.put(DBSchema.DBTable.Cols.REL, faction.getRelationship());

        db.insert(DBSchema.DBTable.NAME, null, cv);
    }
}
