package com.yeumkyuseok.alliesandenemies;

import android.database.Cursor;
import android.database.CursorWrapper;

public class DBCursor extends CursorWrapper {

    public DBCursor(Cursor cursor) {
        super(cursor);
    }

    public Faction getFaction() {
        int id = getInt(getColumnIndex(DBSchema.DBTable.Cols.ID));
        String name = getString(getColumnIndex(DBSchema.DBTable.Cols.NAME));
        int str = getInt(getColumnIndex(DBSchema.DBTable.Cols.STR));
        int rel  = getInt(getColumnIndex(DBSchema.DBTable.Cols.ID));
        return new Faction(id, name, str, rel);
    }
}
