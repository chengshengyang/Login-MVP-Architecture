package com.github.mvp.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/7/12 0012.
 */
public class AppDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "login-mvp-architecture.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UsersPersistenceContract.UserEntry.TABLE_NAME + " (" +
                    UsersPersistenceContract.UserEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + "PRIMARY KEY" + COMMA_SEP +
                    UsersPersistenceContract.UserEntry.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                    UsersPersistenceContract.UserEntry.COLUMN_NAME_PWD + TEXT_TYPE +
                    " )";

    public AppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
