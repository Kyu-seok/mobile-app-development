package com.yeumkyuseok.pracgrader;

public class DBSchema {

    public static class UserTable {
        public static final String NAME = "users";


        public static class Cols {
            public static final String NAME = "name";
            public static final String EMAIL = "email";
            public static final String USERNAME = "user_name ";
            public static final String PASSWORD = "password";
            public static final String COUNTRY = "country";
            public static final String ROLE = "role";
        }
    }

    public static class PracticalTable {
        public static final String NAME = "practicals";

        public static class Cols {
            public static final String TITLE = "title";
            public static final String DESC = "description";
            public static final String MARK = "mark";
            public static final String STUDENT_NAME = "student_name";
        }
    }

}
