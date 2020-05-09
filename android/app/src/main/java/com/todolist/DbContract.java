package com.todolist;
import android.provider.BaseColumns;
import android.content.ContentValues;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import android.database.Cursor;

public final class DbContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DbContract() {}

    /* Inner class that defines the to do table contents */
    public static class ToDoTable implements BaseColumns {
        public static final String NAME = "todo";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_CREATED = "created_at";
        public static final String COLUMN_UPDATED = "updated_at";
    }

    public static final String[] ToDoProjection = {
        BaseColumns._ID,
        ToDoTable.COLUMN_DESCRIPTION.
        ToDoTable.COLUMN_STATUS,
        ToDoTable.COLUMN_CREATED,
        ToDoTable.COLUMN_UPDATED
    };

    public static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + ToDoTable.NAME + " (" +
        ToDoTable._ID + " INTEGER PRIMARY KEY," +
        ToDoTable.COLUMN_DESCRIPTION + " TEXT," +
        ToDoTable.COLUMN_STATUS + " TEXT," +
        ToDoTable.COLUMN_CREATED + " TEXT," +
        ToDoTable.COLUMN_UPDATED + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS " + ToDoTable.NAME;

    private static ContentValues addKeyIfExists(ContentValues values, ReadableMap record, String col) {
        if (record.hasKey(col)) {
          values.put(col, record.getString(col));
        }
        return values;
    }

    public static ContentValues createContentValues(ReadableMap record) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values = addKeyIfExists(values, record, DbContract.ToDoTable.COLUMN_DESCRIPTION);
        values = addKeyIfExists(values, record, DbContract.ToDoTable.COLUMN_STATUS);
        values = addKeyIfExists(values, record, DbContract.ToDoTable.COLUMN_CREATED);
        values = addKeyIfExists(values, record, DbContract.ToDoTable.COLUMN_UPDATED);
        return values;
    }

    public static WritableArray createArrayFromCursor(Cursor cursor) {
        int idx;
        WritableArray returnArray = Arguments.createArray();
        while(cursor.moveToNext()) {
          WritableMap map = Arguments.createMap();
          idx = cursor.getColumnIndexOrThrow(DbContract.ToDoTable._ID);
          map.putInt(DbContract.ToDoTable._ID, cursor.getInt(idx));
          idx = cursor.getColumnIndexOrThrow(DbContract.ToDoTable.COLUMN_DESCRIPTION);
          map.putString(DbContract.ToDoTable.COLUMN_DESCRIPTION, cursor.getString(idx));
          idx = cursor.getColumnIndexOrThrow(DbContract.ToDoTable.COLUMN_STATUS);
          map.putString(DbContract.ToDoTable.COLUMN_STATUS, cursor.getString(idx));
          idx = cursor.getColumnIndexOrThrow(DbContract.ToDoTable.COLUMN_CREATED);
          map.putString(DbContract.ToDoTable.COLUMN_CREATED, cursor.getString(idx));
          idx = cursor.getColumnIndexOrThrow(DbContract.ToDoTable.COLUMN_UPDATED);
          map.putString(DbContract.ToDoTable.COLUMN_UPDATED, cursor.getString(idx));
          returnArray.pushMap(map);
        }
        return returnArray;
    }
}
