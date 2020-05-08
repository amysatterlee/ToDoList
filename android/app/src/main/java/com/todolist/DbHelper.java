package com.todolist;

import com.todolist.DbContract;

import android.content.Context;
import android.content.ContentValues;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Arguments;
import android.database.Cursor;

public class DbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ToDoDb.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbContract.SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DbContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public WritableArray getRecords() {
      SQLiteDatabase db = getReadableDatabase();

      String[] projection = DbContract.ToDoProjection;

      // Filter results WHERE "title" = 'My Title'
      // String selection = ToDoContract.ToDoEntry.COLUMN_NAME_STATUS + " = ?";
      // String[] selectionArgs = { true };

      Cursor cursor = db.query(
        DbContract.ToDoTable.NAME,   // The table to query
        projection,             // The array of columns to return (pass null to get all)
        null,              // selection sThe columns for the WHERE clause
        null,          // selectionArgs The values for the WHERE clause
        null,                   // don't group the rows
        null,                   // don't filter by row groups
        null                    // The sort order
      );

      WritableArray returnArray = Arguments.createArray();
      while(cursor.moveToNext()) {
        WritableMap map = Arguments.createMap();
        int id = cursor.getInt(
            cursor.getColumnIndexOrThrow(DbContract.ToDoTable._ID));
        String description = cursor.getString(
            cursor.getColumnIndexOrThrow(DbContract.ToDoTable.COLUMN_DESCRIPTION));

        map.putInt("id", id);
        map.putString("description", description);
        returnArray.pushMap(map);
      }
      cursor.close();

      return returnArray;
    }

    public boolean insertRecord(ReadableMap record) {
      SQLiteDatabase db = getWritableDatabase();
      String description = record.getString(DbContract.ToDoTable.COLUMN_DESCRIPTION);
      // Create a new map of values, where column names are the keys
      ContentValues values = new ContentValues();
      values.put(DbContract.ToDoTable.COLUMN_DESCRIPTION, description);

      // Insert the new row, returning true if success (id > 0)
      long newRowId = db.insert(DbContract.ToDoTable.NAME, null, values);

      return (newRowId > 0);
    }
}
