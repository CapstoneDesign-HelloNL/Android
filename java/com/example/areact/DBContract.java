package com.example.areact;

public class DBContract {
    private DBContract() {}

    public static final String TABLE = "test";
    public static final String COL_TITLE = "col_title";
    public static final String SQL_CREATE_TB = "CREATE TABLE IF NOT EXISTS " +TABLE + "(" +
            COL_TITLE + " CHAR(20) PRIMARY KEY" + ")";
    public static final String SQL_DROP_TB = "DROP TABLE IF EXISTS " + TABLE;
    public static final String SQL_SELECT = "SELECT * FROM " + TABLE;
    public static final String SQL_INSERT = "INSERT OR REPLACE INTO " + TABLE + "(" + COL_TITLE + ") VALUES ";
    public static final String SQL_DELETE = "DELETE FROM " + TABLE + "WHERE " + COL_TITLE + " = ";
}
