package com.edazh.criminalintent.database;

/**
 * Created by 12392 on 2017/7/9 0009.
 * Crime data 结构类
 */

public class CrimeDbSchema {
    public static final class CrimeTable {
        public static final String NAME = "crime";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
        }
    }
}
