package com.github.mvp.data.source.remote;

import com.github.mvp.data.User;
import com.github.mvp.data.source.UserDataSource;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/11 0011.
 */
public class UserRemoteDataSource implements UserDataSource {

    private static UserRemoteDataSource INSTANCE;

    private final static Map<String, User> USERS_SERVICE_DATA;

    static {
        USERS_SERVICE_DATA = new LinkedHashMap<>();
    }

    public static UserRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRemoteDataSource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private UserRemoteDataSource() {

    }

    @Override
    public void saveUserInfo(User user) {
        USERS_SERVICE_DATA.put(user.getUserId(), user);
    }
}
