package com.example.pesample.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    //context
    private Context context;
    //Database name here
    private static final String DB_NAME = "BookManagement.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_1_NAME = "user";
    private static final String COL_TB1_ID = "_id";
    private static final String COL_TB1_USERNAME = "username";
    private static final String COL_TB1_PASSWORD = "password";
    private static final String COL_TB1_FULL_NAME = "full_name";
    private static final String COL_TB1_ROLE = "role";

    private static final String TABLE_2_NAME = "book";
    private static final String COL_TB2_ID = "_id";
    private static final String COL_TB2_BOOK_NAME = "book_name";
    private static final String COL_TB2_BOOK_AUTHOR = "book_author";
    private static final String COL_TB2_BOOK_PAGES = "book_pages";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_1_NAME +
                " (" + COL_TB1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TB1_FULL_NAME + " TEXT, " +
                COL_TB1_USERNAME + " TEXT, " +
                COL_TB1_PASSWORD + " TEXT, " +
                COL_TB1_ROLE + " TEXT);";
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_2_NAME +
                " (" + COL_TB2_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TB2_BOOK_NAME + " TEXT, " +
                COL_TB2_BOOK_AUTHOR + " TEXT, " +
                COL_TB2_BOOK_PAGES + " INTEGER ); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_1_NAME + ";";
        db.execSQL(query);
        query = "DROP TABLE IF EXISTS " + TABLE_2_NAME + ";";
        db.execSQL(query);
        onCreate(db);
    }
}
