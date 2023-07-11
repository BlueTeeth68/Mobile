package com.example.pesample.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pesample.dao.UserDAO;
import com.example.pesample.database.DBHelper;

public class UserProvider extends ContentProvider {


    public static final String AUTHOR = "com.example.pesample.provider";
    public static final String URL_TABLE_1 = "content://" + AUTHOR + "/" + DBHelper.TABLE_1_NAME;
    public static final String URL_TABLE_2 = "content: //" + AUTHOR + "/" + DBHelper.TABLE_2_NAME;
    public static final UriMatcher uriMatcher;

    private UserDAO userDAO;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHOR, DBHelper.TABLE_1_NAME, 1);
        uriMatcher.addURI(AUTHOR, DBHelper.TABLE_1_NAME + "/#", 2);
        uriMatcher.addURI(AUTHOR, DBHelper.TABLE_2_NAME, 3);
        uriMatcher.addURI(AUTHOR, DBHelper.TABLE_2_NAME + "/#", 4);
    }

    @Override
    public boolean onCreate() {

//        UserDAO userDAO = new UserDAO(getContext());
//        userDAO.open();
        dbHelper = new DBHelper(getContext());
        db = dbHelper.getWritableDatabase();

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case 1:
                cursor = db.query(DBHelper.TABLE_1_NAME, projection,
                        null, null, null, null, null);
                break;
            case 2:
                cursor = db.query(DBHelper.TABLE_1_NAME, projection, "where " + DBHelper.COL_TB1_ID + " = ?",
                        new String[]{uri.getPathSegments().get(1)}, null, null, null);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
