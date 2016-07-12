package com.github.mvp.data.source;

import android.support.annotation.NonNull;

import com.github.mvp.data.User;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/11 0011.
 */
public class UserRepository implements UserDataSource {

    private static UserRepository INSTANCE = null;

    private final UserDataSource mLocalDataSource;

    private final UserDataSource mRemoteDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, User> mCachedUser;

    private UserRepository(@NonNull UserDataSource localData,
                           @NonNull UserDataSource remoteData) {
        this.mLocalDataSource = localData;
        this.mRemoteDataSource = remoteData;
    }

    public static UserRepository getInstance(@NonNull UserDataSource localData,
                                             @NonNull UserDataSource remoteData) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(localData, remoteData);
        }
        return INSTANCE;
    }

    public static void destoryInstance() {
        INSTANCE = null;
    }

    @Override
    public void saveUserInfo(User user) {
        if (null == user) {
            return;
        }

        mLocalDataSource.saveUserInfo(user);
        mRemoteDataSource.saveUserInfo(user);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedUser == null) {
            mCachedUser = new LinkedHashMap<>();
        }
        mCachedUser.put(user.getUserId(), user);
    }
}
