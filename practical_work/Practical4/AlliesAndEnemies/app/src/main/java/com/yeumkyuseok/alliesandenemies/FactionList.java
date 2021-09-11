package com.yeumkyuseok.alliesandenemies;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * Maintains the overall dataset; specifically of course all the different factions.
 */
public class FactionList
{
    private List<Faction> factions = new ArrayList<>();

    public FactionList() {}

    public void load(Context context)
    {
        // ...
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
        // ...

        return factions.size() - 1; // Return insertion point
    }

    public void edit(Faction newFaction)
    {
        // ...
    }

    public void remove(Faction rmFaction)
    {
        factions.remove(rmFaction);
        // ...
    }
}
