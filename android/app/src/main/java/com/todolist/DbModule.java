package com.todolist;

import com.todolist.ToDoDbHelper;
import android.widget.Toast;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;
import com.facebook.react.bridge.Callback;

import java.util.Map;
import java.util.HashMap;

public class DbModule extends ReactContextBaseJavaModule {
  private static ReactApplicationContext reactContext;

  DbModule(ReactApplicationContext context) {
    super(context);
    reactContext = context;
  }

  @Override
  public String getName() {
    return "DbHelper";
  }

  @ReactMethod
  public void get(Callback cb) {
    ToDoDbHelper dbHelper = new ToDoDbHelper(reactContext);
    SQLiteDatabase db = dbHelper.getReadableDatabase();

    // Define a projection that specifies which columns from the database
    // you will actually use after this query.
    String[] projection = {
      BaseColumns._ID,
      ToDoContract.ToDoEntry.COLUMN_NAME_DESCRIPTION
    };

    // Filter results WHERE "title" = 'My Title'
    // String selection = ToDoContract.ToDoEntry.COLUMN_NAME_STATUS + " = ?";
    // String[] selectionArgs = { true };

    Cursor cursor = db.query(
      ToDoContract.ToDoEntry.TABLE_NAME,   // The table to query
      projection,             // The array of columns to return (pass null to get all)
      null,              // selection sThe columns for the WHERE clause
      null,          // selectionArgs The values for the WHERE clause
      null,                   // don't group the rows
      null,                   // don't filter by row groups
      null                    // The sort order
    );

    List items = new ArrayList<>();
    while(cursor.moveToNext()) {
      String description = cursor.getString(
          cursor.getColumnIndexOrThrow(ToDoContract.ToDoEntry.COLUMN_NAME_DESCRIPTION));
      items.add(description);
    }
    cursor.close();

    cb.invoke(items);

    Toast.makeText(getReactApplicationContext(), "done", 1).show();
  }

  @ReactMethod
  public void add(String description) {
    ToDoDbHelper dbHelper = new ToDoDbHelper(reactContext);
    SQLiteDatabase db = dbHelper.getWritableDatabase();

    // Create a new map of values, where column names are the keys
    ContentValues values = new ContentValues();
    values.put(ToDoContract.ToDoEntry.COLUMN_NAME_DESCRIPTION, description);

    // Insert the new row, returning the primary key value of the new row
    long newRowId = db.insert(ToDoContract.ToDoEntry.TABLE_NAME, null, values);

    Toast.makeText(getReactApplicationContext(), "To Do Created - " + newRowId, 1).show();
  }
}
