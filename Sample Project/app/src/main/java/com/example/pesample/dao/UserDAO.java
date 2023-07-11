package com.example.pesample.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pesample.database.DBHelper;
import com.example.pesample.dto.UserDTO;

@SuppressWarnings("unused")
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

    public boolean addUser(UserDTO user) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COL_TB1_FULL_NAME, user.getFullName());
        cv.put(DBHelper.COL_TB1_USERNAME, user.getUsername());
        cv.put(DBHelper.COL_TB1_ROLE, "User");
        cv.put(DBHelper.COL_TB1_PASSWORD, "0000");
        long result = db.insert(DBHelper.TABLE_1_NAME, null, cv);

        return result > -1;
    }

    public Cursor findAll() {
        String[] projection = new String[]{
                DBHelper.COL_TB1_ID,
                DBHelper.COL_TB1_USERNAME,
                DBHelper.COL_TB1_FULL_NAME,
                DBHelper.COL_TB1_ROLE};

        return db.query(DBHelper.TABLE_1_NAME, projection,
                null, null, null, null, null);
    }

    public Cursor findById(String id) {
        String[] projection = new String[]{
                DBHelper.COL_TB1_ID,
                DBHelper.COL_TB1_USERNAME,
                DBHelper.COL_TB1_FULL_NAME,
                DBHelper.COL_TB1_ROLE};
        String selection = DBHelper.COL_TB1_ID + " = ?";
        String[] selectionArg = new String[]{id};
        Cursor result = db.query(DBHelper.TABLE_1_NAME,
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
                DBHelper.COL_TB1_ID,
                DBHelper.COL_TB1_USERNAME,
                DBHelper.COL_TB1_FULL_NAME,
                DBHelper.COL_TB1_ROLE};
        String selection = DBHelper.COL_TB1_USERNAME + " = ? AND " + DBHelper.COL_TB1_PASSWORD + " = ?";
        String[] selectionArg = new String[]{username, password};
        Cursor result = db.query(DBHelper.TABLE_1_NAME,
                projection,
                selection,
                selectionArg,
                null, null, null);

        if (result != null) {
            result.moveToFirst();
        }
        return result;
    }

    public boolean updateUser(String id, String username, String fullName, String role) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COL_TB1_USERNAME, username);
        cv.put(DBHelper.COL_TB1_FULL_NAME, fullName);
        cv.put(DBHelper.COL_TB1_ROLE, role);
        String where = DBHelper.COL_TB1_ID + " = ? ";
        String[] whereArg = new String[]{id};
        long result = db.update(DBHelper.TABLE_1_NAME, cv, where, whereArg);
        return result > 0;
    }

    public boolean deleteById(String id) {
        String where = DBHelper.COL_TB1_ID + " = ?";
        String[] whereArg = new String[]{id};
        int result = db.delete(DBHelper.TABLE_1_NAME, where, whereArg);
        return result >= 0;

    }

    public void clearData() {
        db.execSQL("Delete from " + DBHelper.TABLE_1_NAME);
    }
}
