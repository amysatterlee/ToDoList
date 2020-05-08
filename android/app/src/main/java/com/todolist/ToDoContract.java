package com.todolist;
import android.provider.BaseColumns;

public final class ToDoContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ToDoContract() {}

    /* Inner class that defines the table contents */
    public static class ToDoEntry implements BaseColumns {
        public static final String TABLE_NAME = "todo";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_CREATED = "created_at";
        public static final String COLUMN_NAME_UPDATED = "updated_at";
    }

    public static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + ToDoEntry.TABLE_NAME + " (" +
        ToDoEntry._ID + " INTEGER PRIMARY KEY," +
        ToDoEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
        ToDoEntry.COLUMN_NAME_STATUS + " TEXT," +
        ToDoEntry.COLUMN_NAME_CREATED + " TEXT," +
        ToDoEntry.COLUMN_NAME_UPDATED + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS " + ToDoEntry.TABLE_NAME;
}
