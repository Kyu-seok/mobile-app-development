package com.yeumkyuseok.alliesandenemies;

/**
 * Represents one of the various factions.
 */
public class Faction
{
    public static final int ENEMY = 0;
    public static final int NEUTRAL = 1;
    public static final int ALLY = 2;

    public static final int DEFAULT_STRENGTH = 100;
    public static final int DEFAULT_RELATIONSHIP = NEUTRAL;

    private static int nextId = 0;

    public final int id;
    private String name;
    private int strength;
    private int relationship;

    /**
     * Creates a new Faction by explicitly giving the ID, as well as other information.
     */
    public Faction(int id, String name, int strength, int relationship)
    {
        if(!(relationship == ENEMY || relationship == NEUTRAL || relationship == ALLY))
        {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.name = name;
        this.strength = strength;
        this.relationship = relationship;
        nextId = id + 1;
    }

    /**
     * Creates a new Faction, and auto-generates a new ID (based on the next unused one).
     */
    public Faction(String name, int strength, int relationship)
    {
        this(nextId, name, strength, relationship); // Delegate to the other constructor
        nextId++;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getStrength()
    {
        return strength;
    }

    public int getRelationship()
    {
        return relationship;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setStrength(int strength)
    {
        this.strength = strength;
    }

    public void setRelationship(int relationship)
    {
        if(!(relationship == ENEMY || relationship == NEUTRAL || relationship == ALLY))
        {
            throw new IllegalArgumentException();
        }
        this.relationship = relationship;
    }
}
