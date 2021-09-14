package com.yeumkyuseok.alliesandenemies;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Maintains the overall dataset; specifically of course all the different factions.
 */
public class FactionList
{
    private static final String TAG = "FactionList";
    private List<Faction> factions = new ArrayList<>();
    DBModel dbModel = new DBModel();
    Cursor cursor;

    public FactionList() {}

    public void load(Context context)
    {
        DBHelper dbHelper = new DBHelper(context);
        dbModel.db = dbHelper.getReadableDatabase();
        cursor = dbModel.db.rawQuery("SELECT * FROM factions", null);
        DBCursor dbCursor;

        while (cursor.moveToNext()) {
            dbCursor = new DBCursor(cursor);
            factions.add(dbCursor.getFaction());
        }

        // dbCursor.getFaction();
        // set factions list from loaded db using cursor
    }

    public int size()
    {
        return factions.size();
    }

    public Faction get(int i)
    {
        return factions.get(i);
    }

    public int add(Faction newFaction)
    {
        factions.add(newFaction);
        dbModel.addFaction(newFaction);

        return factions.size() - 1; // Return insertion point
    }

    public void edit(Faction newFaction)
    {
        int ID = newFaction.getId();
        int index = 0;
        for (int i = 0; i < factions.size(); i++) {
            if (factions.get(i).getId() == ID) {
                index = i;
            }
        }
        factions.set(index, newFaction);
        String logout = "\n";


        for (int i = 0; i < factions.size(); i++) {
            logout += factions.get(i).getName() + " " + factions.get(i).getRelationship()  + "\n";
        }


        dbModel.updateFaction(newFaction);
        Log.d(TAG, "edit: " + logout);
        Log.d(TAG, "NEW_FACTION : " + newFaction.getName() + " " + newFaction.getId() + " " + newFaction.getRelationship());
    }

    public void remove(Faction rmFaction)
    {
        factions.remove(rmFaction);
        dbModel.removeFaction(rmFaction);
        // ...
    }
}
