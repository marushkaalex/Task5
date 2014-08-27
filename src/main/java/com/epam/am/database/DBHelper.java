package com.epam.am.database;

public class DBHelper {
    public class UserTable {
        public static final String TABLE = "user";
        public static final String USERNAME = "username";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String ROLE = "role";
        public static final String ID = "id";
        public static final String DATE_OF_BIRTH = "dob";

        private UserTable() {
        }
    }

    public class PaintingTable {
        public static final String TABLE = "painting";
        public static final String USER_PAINTING_TABLE = "user_painting";
        public static final String ID = "id";
        public static final String USER_ID = "artist_id";
        public static final String LIKES = "likes";
        public static final String PATH = "path";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String DATE = "date";

        private PaintingTable() {
        }
    }
}
