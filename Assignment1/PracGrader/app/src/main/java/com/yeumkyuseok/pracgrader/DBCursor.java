package com.yeumkyuseok.pracgrader;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.yeumkyuseok.pracgrader.DBSchema.*;

public class DBCursor extends CursorWrapper {

    public DBCursor(Cursor cursor) {
        super(cursor);
    }

    public User getUser() {
        String name = getString(getColumnIndex(UserTable.Cols.NAME));
        String email = getString(getColumnIndex(UserTable.Cols.EMAIL));
        String user_name = getString(getColumnIndex(UserTable.Cols.USERNAME));
        String password = getString(getColumnIndex(UserTable.Cols.PASSWORD));
        String country = getString(getColumnIndex(UserTable.Cols.COUNTRY));
        int role = getInt(getColumnIndex(UserTable.Cols.ROLE));
        int added_by = getInt(getColumnIndex(UserTable.Cols.ADDEDBY));
        return new User(name, email, user_name, password, country, role, added_by);

    }
}
