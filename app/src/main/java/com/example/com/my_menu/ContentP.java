package com.example.com.my_menu;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/17.
 */
public class ContentP extends ContentProvider {
    private static UriMatcher matcher = new UriMatcher(-1);
    private SqliteDBHelper mdbhelper;
    private SQLiteDatabase mdb;

    static {
        matcher.addURI("com.example.com.visitedDB", "query", 1);
        matcher.addURI("com.example.com.visitedDB", "insert", 2);
        matcher.addURI("com.example.com.visitedDB", "delete", 3);
        matcher.addURI("com.example.com.visitedDB", "update", 4);

    }

    @Override
    public boolean onCreate() {
        mdbhelper = new SqliteDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor cursor = null;
        if (matcher.match(uri) == 1) {
            mdb = mdbhelper.getReadableDatabase();
            cursor = mdb.query("student", strings, s, strings1, null, null, s1);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        if (matcher.match(uri) == 2) {
            mdb = mdbhelper.getWritableDatabase();
            mdb.insert("student", null, contentValues);
        }
        return uri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        int i=1;
        mdb = mdbhelper.getWritableDatabase();
        mdb.delete("student",null,null);
        return i;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
