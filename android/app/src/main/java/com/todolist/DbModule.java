package com.todolist;

import com.todolist.DbHelper;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Callback;

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
    // use DbHelper to fetch records from DB
    DbHelper dbHelper = new DbHelper(reactContext);
    WritableArray returnArray = dbHelper.getRecords();
    // pass return array back to react via callback
    cb.invoke(returnArray);
  }

  @ReactMethod
  public void add(ReadableMap record, Callback cb) {
    DbHelper dbHelper = new DbHelper(reactContext);
    boolean saved = dbHelper.insertRecord(record);

    cb.invoke(saved);
  }
}
