package com.yeumkyuseok.alliesandenemies;

public class DBSchema {

    public static class DBTable {
        public static final String NAME = "factions";

        public static class Cols {
            public static final String ID = "faction_id";
            public static final String NAME = "faction_name";
            public static final String STR = "faction_strength";
            public static final String REL = "faction_relationship";
        }
    }

}
