package com.silvia.fmi.courceproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.naming.Context;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "NOTES";

    public static final String _ID = "id";
    public static final String SUBJECT = "Zaglavie";
    public static final String DESC = "Description";

    static final String DB_NAME = "UserNotes=DB";

    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT , " + SUBJECT + " TEXT NOT NULL, " + DESC + " TEXT);";

    public DataBaseHelper(Context context){super (context, DB_NAME , null , DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) { db.execSQL(CREATE_TABLE);}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                onCreate(db);

    }
}
