package com.todolist;
import android.provider.BaseColumns;

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
      ToDoTable.COLUMN_DESCRIPTION
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
}
