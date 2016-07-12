package com.github.mvp.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.github.mvp.data.User;
import com.github.mvp.data.source.UserDataSource;

/**
 * Created by Administrator on 2016/7/11 0011.
 */
public class UserLocalDataSource implements UserDataSource {

    private static UserLocalDataSource INSTANCE = null;

    private AppDbHelper mAppDbHelper;

    private UserLocalDataSource(@NonNull Context context) {
        mAppDbHelper = new AppDbHelper(context);
    }

    public static UserLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UserLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void saveUserInfo(User user) {
        if (user == null) {
            return;
        }

        SQLiteDatabase db = mAppDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersPersistenceContract.UserEntry.COLUMN_NAME_ENTRY_ID, user.getUserId());
        values.put(UsersPersistenceContract.UserEntry.COLUMN_NAME_EMAIL, user.getUserEmail());
        values.put(UsersPersistenceContract.UserEntry.COLUMN_NAME_PWD, user.getPassword());

        db.insert(UsersPersistenceContract.UserEntry.TABLE_NAME, null, values);
        db.close();
    }
}
