package com.silvia.fmi.courceproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;

public class DbManager {

    private DataBaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DbManager(MainActivity c) {
        context = c;
    }

    public DbManager open() throws SQLException {
        dbHelper = new DataBaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.SUBJECT, name);
        contentValues.put(DataBaseHelper.DESC, desc);
        database.insert(DataBaseHelper.TABLE_NAME, null, contentValues);
    }

    public Cursor fetch() {
        String[] columns = new String[]{DataBaseHelper._ID, DataBaseHelper.SUBJECT, DataBaseHelper.DESC};
        Cursor cursor = database.query(DataBaseHelper.TABLE_NAME, columns,
                null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public ArrayList<String> getDb() {
        Cursor c = database.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_NAME, null);
        c.moveToNext();
        ArrayList<String> result = new ArrayList<String>();
        result.add(c.getString(c.getColumnIndex(DataBaseHelper.SUBJECT)));
                return result;
    }

    public int upgrate(long _id,String name, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.SUBJECT, name);
        contentValues.put(DataBaseHelper.DESC, desc);
        int i = database.update(DataBaseHelper.TABLE_NAME,contentValues, DataBaseHelper._ID+"="+_id, null);
                return i;
    }

    public  void delete(long _id){
        database.delete(DataBaseHelper.TABLE_NAME,DataBaseHelper._ID+"="+_id, null);
    }

}
