package com.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class ToDoDbHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + ToDoEntry.TABLE_NAME + " (" +
        ToDoEntry._ID + " INTEGER PRIMARY KEY," +
        ToDoEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
        ToDoEntry.COLUMN_NAME_STATUS + " TEXT," +
        ToDoEntry.COLUMN_NAME_CREATED + " TEXT," +
        ToDoEntry.COLUMN_NAME_UPDATED + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS " + ToDoEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ToDoDb.db";

    public ToDoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
