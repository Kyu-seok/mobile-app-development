package com.yeumkyuseok.mathtest;

public class DBSchema {

    public static class UserTable {
        public static final String NAME = "users";


        public static class Cols {
            public static final String NAME = "name";
            public static final String EMAIL = "email";
            public static final String USERNAME = "user_name";
            public static final String PASSWORD = "password";
            public static final String COUNTRY = "country";
            public static final String ROLE = "role";
            public static final String ADDEDBY = "added_by";
        }
    }

    public static class PracticalTable {
        public static final String NAME = "practicals";

        public static class Cols {
            public static final String TITLE = "title";
            public static final String DESC = "description";
            public static final String MARK = "total_mark";
        }
    }

    public static class TakenPracTable {
        public static final String NAME = "takenPrac";

        public static class Cols {
            public static final String USERNAME = "user_name";
            public static final String PRAC_TITLE = "prac_title";
            public static final String MARK_SCORED = "mark_scored";
        }
    }

}