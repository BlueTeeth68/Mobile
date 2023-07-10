package com.example.pesample.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pesample.database.DBHelper;
import com.example.pesample.dto.UserDTO;

public class UserDAO {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public UserDAO(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean AddUser(UserDTO user) {
        ContentValues cv = new ContentValues();
        cv.put(dbHelper.COL_TB1_FULL_NAME, user.getFullName());
        cv.put(dbHelper.COL_TB1_USERNAME, user.getUsername());
        cv.put(dbHelper.COL_TB1_ROLE, "User");
        cv.put(dbHelper.COL_TB1_PASSWORD, "0000");
        long result = db.insert(dbHelper.TABLE_1_NAME, null, cv);

        if (result > -1) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor findAll() {
        String[] projection = new String[]{
                dbHelper.COL_TB1_ID,
                dbHelper.COL_TB1_USERNAME,
                dbHelper.COL_TB1_FULL_NAME,
                dbHelper.COL_TB1_ROLE};

        Cursor result = db.query(dbHelper.TABLE_1_NAME, projection,
                null, null, null, null, null);
        return result;
    }

    public Cursor findById(String id) {
        String[] projection = new String[]{
                dbHelper.COL_TB1_ID,
                dbHelper.COL_TB1_USERNAME,
                dbHelper.COL_TB1_FULL_NAME,
                dbHelper.COL_TB1_ROLE};
        String selection = dbHelper.COL_TB1_ID + " = ?";
        String[] selectionArg = new String[]{id};
        Cursor result = db.query(dbHelper.TABLE_1_NAME,
                projection,
                selection,
                selectionArg,
                null, null, null);
        if (result != null) {
            result.moveToFirst();
        }
        return result;
    }

    public Cursor login(String username, String password) {
        String[] projection = new String[]{
                dbHelper.COL_TB1_ID,
                dbHelper.COL_TB1_USERNAME,
                dbHelper.COL_TB1_FULL_NAME,
                dbHelper.COL_TB1_ROLE};
        String selection = dbHelper.COL_TB1_USERNAME + " = ? AND " + dbHelper.COL_TB1_PASSWORD + " = ?";
        String[] selectionArg = new String[]{username, password};
        Cursor result = db.query(dbHelper.TABLE_1_NAME,
                projection,
                selection,
                selectionArg,
                null, null, null);

        if(result != null) {
            result.moveToFirst();
        }
        return result;
    }
}